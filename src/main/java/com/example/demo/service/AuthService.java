package com.example.demo.service;

import com.example.demo.dto.JwtResponse;
import com.example.demo.security.JwtIO;
import com.example.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtIO;
    @Autowired
    private DateUtils dateUtils;

    @Value("${jms.jwt.token.expiresIn}")
    private int EXPIRES_IN;
    public JwtResponse login(String clientId, String clientSecret) {

        JwtResponse jwt = JwtResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtIO.generateToken("Wiki Wiki"))
                .issueAt(dateUtils.getDateMillis()+"")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return  jwt;
    }

}
