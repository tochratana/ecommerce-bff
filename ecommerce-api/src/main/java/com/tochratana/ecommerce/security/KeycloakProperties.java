package com.tochratana.ecommerce.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Data
public class KeycloakProperties {
    private String clientId;
    private String clientSecret;
    private String realm;
    private String serverUrl;
    private String openidConfigurationUrl;
}
