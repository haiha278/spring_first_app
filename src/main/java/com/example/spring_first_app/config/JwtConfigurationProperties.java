package com.example.spring_first_app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfigurationProperties {
    private String secret;
    private String refreshSecret;
    private Expiration expiration;

    @Data
    public static class Expiration {
        private Long jwt;
        private Long refreshJwt;
    }
}
