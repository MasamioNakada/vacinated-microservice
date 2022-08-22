package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJwtIOConfig implements WebMvcConfigurer {
    @Value("${jms.jwt.security.enable:false}")
    private boolean securityEnable;

    @Autowired
    private InterceptorJwtIO interceptorJwtIO;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(securityEnable){
            registry.addInterceptor(interceptorJwtIO)
                    .excludePathPatterns(
                            "/student/oauth/client_credential/accesstoken",
                            "/v2/api-docs",
                            "/swagger-resources/**",
                            "/swagger-ui.html",
                            "/webjars/**");
        }
    }

}
