package com.example.demo.dto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class JwtController {

    @GetMapping(path = "/version")
    public ResponseEntity<Object>version(){
        return ResponseEntity.ok("Hola");
    }
}
