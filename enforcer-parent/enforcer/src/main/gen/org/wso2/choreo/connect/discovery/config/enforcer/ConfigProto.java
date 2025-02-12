// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wso2/discovery/config/enforcer/config.proto

package org.wso2.choreo.connect.discovery.config.enforcer;

public final class ConfigProto {
  private ConfigProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wso2_discovery_config_enforcer_Config_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wso2_discovery_config_enforcer_Config_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n+wso2/discovery/config/enforcer/config." +
      "proto\022\036wso2.discovery.config.enforcer\032)w" +
      "so2/discovery/config/enforcer/cert.proto" +
      "\032,wso2/discovery/config/enforcer/service" +
      ".proto\0322wso2/discovery/config/enforcer/j" +
      "wt_generator.proto\032/wso2/discovery/confi" +
      "g/enforcer/jwt_issuer.proto\032/wso2/discov" +
      "ery/config/enforcer/throttling.proto\032*ws" +
      "o2/discovery/config/enforcer/cache.proto" +
      "\032.wso2/discovery/config/enforcer/analyti" +
      "cs.proto\032-wso2/discovery/config/enforcer" +
      "/security.proto\032/wso2/discovery/config/e" +
      "nforcer/management.proto\0320wso2/discovery" +
      "/config/enforcer/rest_server.proto\032+wso2" +
      "/discovery/config/enforcer/filter.proto\032" +
      ",wso2/discovery/config/enforcer/tracing." +
      "proto\032,wso2/discovery/config/enforcer/me" +
      "trics.proto\032)wso2/discovery/config/enfor" +
      "cer/soap.proto\"\225\007\n\006Config\022:\n\010security\030\001 " +
      "\001(\0132(.wso2.discovery.config.enforcer.Sec" +
      "urity\022;\n\010keystore\030\002 \001(\0132).wso2.discovery" +
      ".config.enforcer.CertStore\022=\n\ntruststore" +
      "\030\003 \001(\0132).wso2.discovery.config.enforcer." +
      "CertStore\022<\n\013authService\030\004 \001(\0132\'.wso2.di" +
      "scovery.config.enforcer.Service\022B\n\014jwtGe" +
      "nerator\030\005 \001(\0132,.wso2.discovery.config.en" +
      "forcer.JWTGenerator\022>\n\nthrottling\030\006 \001(\0132" +
      "*.wso2.discovery.config.enforcer.Throttl" +
      "ing\0224\n\005cache\030\007 \001(\0132%.wso2.discovery.conf" +
      "ig.enforcer.Cache\022<\n\tjwtIssuer\030\010 \001(\0132).w" +
      "so2.discovery.config.enforcer.JWTIssuer\022" +
      "<\n\tanalytics\030\t \001(\0132).wso2.discovery.conf" +
      "ig.enforcer.Analytics\022>\n\nmanagement\030\n \001(" +
      "\0132*.wso2.discovery.config.enforcer.Manag" +
      "ement\022>\n\nrestServer\030\013 \001(\0132*.wso2.discove" +
      "ry.config.enforcer.RestServer\0228\n\007tracing" +
      "\030\014 \001(\0132\'.wso2.discovery.config.enforcer." +
      "Tracing\0228\n\007metrics\030\r \001(\0132\'.wso2.discover" +
      "y.config.enforcer.Metrics\0227\n\007filters\030\016 \003" +
      "(\0132&.wso2.discovery.config.enforcer.Filt" +
      "er\0222\n\004soap\030\017 \001(\0132$.wso2.discovery.config" +
      ".enforcer.SoapB\222\001\n1org.wso2.choreo.conne" +
      "ct.discovery.config.enforcerB\013ConfigProt" +
      "oP\001ZNgithub.com/envoyproxy/go-control-pl" +
      "ane/wso2/discovery/config/enforcer;enfor" +
      "cerb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.wso2.choreo.connect.discovery.config.enforcer.CertStoreProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.ServiceProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.JWTGeneratorProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.JWTIssuerProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.ThrottlingProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.CacheProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.AnalyticsProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.SecurityProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.ManagementProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.RestServerProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.FilterProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.TracingProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.MetricsProto.getDescriptor(),
          org.wso2.choreo.connect.discovery.config.enforcer.soapProto.getDescriptor(),
        });
    internal_static_wso2_discovery_config_enforcer_Config_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_wso2_discovery_config_enforcer_Config_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wso2_discovery_config_enforcer_Config_descriptor,
        new java.lang.String[] { "Security", "Keystore", "Truststore", "AuthService", "JwtGenerator", "Throttling", "Cache", "JwtIssuer", "Analytics", "Management", "RestServer", "Tracing", "Metrics", "Filters", "Soap", });
    org.wso2.choreo.connect.discovery.config.enforcer.CertStoreProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.ServiceProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.JWTGeneratorProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.JWTIssuerProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.ThrottlingProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.CacheProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.AnalyticsProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.SecurityProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.ManagementProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.RestServerProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.FilterProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.TracingProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.MetricsProto.getDescriptor();
    org.wso2.choreo.connect.discovery.config.enforcer.soapProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
