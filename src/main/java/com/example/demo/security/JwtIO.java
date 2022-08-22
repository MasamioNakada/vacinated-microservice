package com.example.demo.security;

import com.example.demo.util.GsonUtils;
import com.google.gson.Gson;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@Component
public class JwtIO {

    @Value("${jms.jwt.token.secret}")
    private String SECRET;
    @Value("${jms.jwt.timezone}")
    private String TIMEZONE;
    @Value("${jms.jwt.token.expiresIn}")
    private int EXPIRES_IN;
    @Value("${jms.jwt.issuer}")
    private String ISSUER;

    public String generateToken(Object src) {
        String subject = GsonUtils.serializae(src);

        Signer signer = HMACSigner.newSHA256Signer(SECRET);
        TimeZone tz = TimeZone.getTimeZone(TIMEZONE);

        ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRES_IN);

        JWT jwt = new JWT()
                .setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
                .setSubject(subject)
                .setExpiration(zdt);
        return JWT.getEncoder().encode(jwt, signer);
    }

    public Boolean validateToken(String encodeJWT){

        boolean result=false;
        try{
            JWT jwt = jwt(encodeJWT);
            return jwt.isExpired();
        } catch (Exception a) {
            result =  true;
        }
        return result;
    }

    public String getPayload(String encodeJWT){

        JWT jwt = jwt(encodeJWT);
        return jwt.subject;
    }

    public JWT jwt(String encodedJWT){
        JWT jwt = JWTUtils.decodePayload(encodedJWT);
        return jwt;

    }

}
