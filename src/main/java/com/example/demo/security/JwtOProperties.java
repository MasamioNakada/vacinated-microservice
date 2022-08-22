package com.example.demo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.Security;

@Data
@Configuration
@ConfigurationProperties(prefix = "jms.jwt")
public class JwtOProperties {

    private Security security;
    private String timezone ;
    private String issuer ;
    private Token token;
    private Excluded excluded;

    @Data
    public static class Security{
        private boolean enable  = true;
    }

    @Data
    public static class Token {

        private Auth auth;
        private String secret;
        private int expiresIn = 3600;

    }

    public static class Auth{
        private String path ;
    }

    @Data
    public static class Excluded {
        private String path ;

    }

}
