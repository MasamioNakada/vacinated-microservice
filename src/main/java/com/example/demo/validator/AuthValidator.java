package com.example.demo.validator;

import com.example.demo.exceptions.Apiunauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {

    private static final String CLIENT_CREDENTIAL="client_credential";

    public void validate(MultiValueMap<String, String> paramMap, String grantType ) throws Apiunauthorized {
        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIAL)){
            message("EL campo grantType es invalido");
        }
        if(Objects.isNull(paramMap) || paramMap.getFirst("client_id").isEmpty() || paramMap.getFirst("client_secret").isEmpty()){
            message("client_id o client_secret no debe ser vacio");
        }
    }

    public void message( String message) throws Apiunauthorized{
        throw new Apiunauthorized(message);
    }

}
