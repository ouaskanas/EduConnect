package com.ouaskanas.educonnect.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@EnableAutoConfiguration
public class JwtGenerator {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication auth) {
        String username = auth.getName();
        Date current_Date = new Date();
        Date expiration_Date = new Date(current_Date.getTime() + 1000 * 60 * 60 * 24);
        System.out.println("Expire date :"+expiration_Date);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(current_Date)
                .setExpiration(expiration_Date)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
        return token;
    }

    public String getUsernameFromJwt(String token) {
        System.out.println("token : "+token);
        token = token.replace("Bearer ", "");
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        System.out.println("claims : "+claims.getSubject());
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            throw new AuthenticationCredentialsNotFoundException("Token expired");
        }
    }

}
