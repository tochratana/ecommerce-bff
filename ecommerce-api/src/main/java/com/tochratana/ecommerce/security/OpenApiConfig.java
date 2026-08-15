package com.tochratana.ecommerce.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final KeycloakProperties keycloakProperties;

    @Bean
    public OpenAPI ecommerceOpenApi() {
        SecurityScheme keycloakSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.OPENIDCONNECT)
                .scheme("bearer")
                .openIdConnectUrl(keycloakProperties.getOpenidConfigurationUrl());

        return new OpenAPI()
                .info(new Info()
                        .title("Learn Spring API")
                        .version("v1"))
                .components(new Components()
                        .addSecuritySchemes("keycloak", keycloakSecurityScheme));
    }
}
