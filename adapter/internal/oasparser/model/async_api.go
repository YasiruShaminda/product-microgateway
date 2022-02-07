/*
 *  Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package model

import (
	"errors"

	"github.com/wso2/product-microgateway/adapter/internal/loggers"
	"github.com/wso2/product-microgateway/adapter/internal/oasparser/constants"
)

// AsyncAPI is the struct for the AsyncAPI 2.0.0 definition
type AsyncAPI struct {
	SpecVersion string `json:"asyncapi,omitempty"`
	ID          string `json:"id,omitempty"`
	Info        struct {
		Title   string `json:"title,omitempty"`
		Version string `json:"version,omitempty"`
	} `json:"info,omitempty"`
	Servers struct {
		Production Server `json:"production,omitempty"`
		Sandbox    Server `json:"sandbox,omitempty"`
	} `json:"servers,omitempty"`
	Channels   map[string]ChannelItem `json:"channels,omitempty"`
	Components struct {
		Schemas         map[string]interface{}    `json:"schemas,omitempty"`
		Messages        map[string]interface{}    `json:"messages,omitempty"`
		SecuritySchemes map[string]SecurityScheme `json:"securitySchemes,omitempty"`
	} `json:"components,omitempty"`
	VendorExtensions map[string]interface{} `json:"-"`
}

// Server object in AsyncAPI
type Server struct {
	URL             string                 `json:"url,omitempty"`
	Protocol        string                 `json:"protocol,omitempty"`
	ProtocolVersion string                 `json:"protocolVersion,omitempty"`
	Variables       map[string]interface{} `json:"variables,omitempty"`
	Security        []map[string][]string  `json:"security,omitempty"`
	Bindings        map[string]interface{} `json:"bindings,omitempty"`
}

// ChannelItem in AsyncAPI channels
type ChannelItem struct {
	Ref              string                 `json:"$ref,omitempty"`
	Subscribe        interface{}            `json:"subscribe,omitempty"` // TODO: (suksw) OperationAsync or $ref
	Publish          interface{}            `json:"publish,omitempty"`   // TODO: (suksw) OperationAsync or $ref
	Parameters       map[string]interface{} `json:"parameters,omitempty"`
	Bindings         map[string]interface{} `json:"bindings,omitempty"`
	VendorExtensions map[string]interface{} `json:"-"`
}

// OperationAsync is the Operation object that includes the message object
type OperationAsync struct {
	OperationID string `json:"operationId,omitempty"`
	Message     struct {
		Headers       string `json:"headers,omitempty"`
		Payload       string `json:"payload,omitempty"`
		CorrelationID string `json:"correlationId,omitempty"`
		SchemaFormat  string `json:"schemaFormat,omitempty"`
		ContentType   string `json:"contentType,omitempty"`
		Name          string `json:"name,omitempty"`
		Title         string `json:"title,omitempty"`
	}
}

// SetInfoAsyncAPI populates the MgwSwagger object with information in asyncapi.yaml.
func (swagger *MgwSwagger) SetInfoAsyncAPI(asyncAPI AsyncAPI) error {
	swagger.vendorExtensions = asyncAPI.VendorExtensions
	swagger.securityScheme = asyncAPI.getSecuritySchemes()
	swagger.resources = asyncAPI.getResources()

	if asyncAPI.Servers.Production.URL != "" {
		endpoint, err := getWebSocketEndpoint(asyncAPI.Servers.Production.URL)
		if err == nil {
			productionEndpoints := append([]Endpoint{}, *endpoint)
			swagger.productionEndpoints = generateEndpointCluster(constants.ProdClustersConfigNamePrefix,
				productionEndpoints, constants.LoadBalance)
		} else {
			return errors.New("error encountered when parsing the production endpoint for AsyncAPI")
		}
	}
	if asyncAPI.Servers.Sandbox.URL != "" {
		endpoint, err := getWebSocketEndpoint(asyncAPI.Servers.Sandbox.URL)
		if err == nil {
			sandboxEndpoints := append([]Endpoint{}, *endpoint)
			swagger.sandboxEndpoints = generateEndpointCluster(constants.SandClustersConfigNamePrefix,
				sandboxEndpoints, constants.LoadBalance)
		} else {
			return errors.New("error encountered when parsing the sandbox endpoint for AsyncAPI")
		}
	}
	return nil
}

func (asyncAPI AsyncAPI) getSecuritySchemes() []SecurityScheme {
	securitySchemes := []SecurityScheme{}
	for key, securityScheme := range asyncAPI.Components.SecuritySchemes {
		securityScheme.DefinitionName = key
		securitySchemes = append(securitySchemes, securityScheme)
	}
	return securitySchemes
}

func (asyncAPI AsyncAPI) getResources() []*Resource {
	resources := []*Resource{}
	for channel, channelItem := range asyncAPI.Channels {
		var methodsArray []*Operation
		var vendorExtensions map[string]interface{}
		if channelItem.Publish == nil && channelItem.Subscribe == nil {
			loggers.LoggerOasparser.Warnf(
				"The topic does not include a Publish or a Subscribe definition. Discarding the topic %v.", channel)
			continue
		} else if channelItem.Publish != nil && channelItem.Subscribe != nil {
			loggers.LoggerOasparser.Warnf(
				"Both Publish and Subscribe types exists for the same topic. Prioritizing type Publish for topic %v.", channel)
			vendorExtensions = channelItem.Publish.(map[string]interface{})
		} else if channelItem.Publish != nil { // only Publish has been defined
			vendorExtensions = channelItem.Publish.(map[string]interface{})
		} else { // only Subscribe has been defined
			vendorExtensions = channelItem.Subscribe.(map[string]interface{})
		}

		security := getSecurityArray(vendorExtensions)
		methodsArray = append(methodsArray, NewOperation("GET", security, vendorExtensions, MockedAPIConfig{}))
		resource := unmarshalSwaggerResources(channel, methodsArray, channelItem.VendorExtensions)
		resources = append(resources, &resource)
	}

	return SortResources(resources)
}

func getSecurityArray(vendorExtensions map[string]interface{}) (security []map[string][]string) {
	if vendorExtensions[constants.XScopes] != nil {
		securityItem := make(map[string][]string)
		rawScopes := vendorExtensions[constants.XScopes].([]interface{})
		var scopes []string

		for _, rawScope := range rawScopes {
			scopes = append(scopes, rawScope.(string))
		}
		securityItem[constants.APIMOauth2Type] = scopes
		security = append(security, securityItem)
	}
	return security
}
