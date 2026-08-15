package com.tochratana.learnSpring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {

    @Value("${file-upload.client-path}")
    private String clientPath;

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDirectory = Paths.get(serverPath).toAbsolutePath().normalize();
        String resourceLocation = uploadDirectory.toUri().toString();
        if (!resourceLocation.endsWith("/")) {
            resourceLocation += "/";
        }

        registry.addResourceHandler(stripTrailingSlash(clientPath) + "/**")
                .addResourceLocations(resourceLocation);
    }

    private static String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
