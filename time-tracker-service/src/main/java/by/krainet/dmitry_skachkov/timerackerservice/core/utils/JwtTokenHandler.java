package by.krainet.dmitry_skachkov.timerackerservice.core.utils;

import by.dmitryskachkov.exception.exceptions.TokenException;
import by.krainet.dmitry_skachkov.timerackerservice.conf.prop.JWTProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenHandler {

    private final JWTProperties properties;

    private final ObjectMapper objectMapper;

    public JwtTokenHandler(JWTProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    public String generateAccessToken(UserSecurity userSecurity) {
        try {
            long expirationTimeInMillis = 7 * 24 * 60 * 60 * 1000;

            String token = Jwts.builder()
                    .setSubject(objectMapper.writeValueAsString(userSecurity))
                    .setIssuer(properties.getIssuer())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                    .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                    .compact();
            addToContext(userSecurity);
            return token;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToContext(UserSecurity userSecurity) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userSecurity, null, userSecurity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public UserSecurity getUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();
        try {
            return objectMapper.readValue(claims.getSubject(), UserSecurity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            TokenException exception = new TokenException("Invalid JWT signature");
            exception.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw exception;
        } catch (MalformedJwtException ex) {
            TokenException exception = new TokenException("Invalid JWT token");
            exception.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw exception;
        } catch (ExpiredJwtException ex) {
            TokenException exception = new TokenException("Expired JWT token");
            exception.setHttpStatusCode(HttpStatus.FORBIDDEN);
            throw exception;
        } catch (UnsupportedJwtException ex) {
            TokenException exception = new TokenException("Unsupported JWT token");
            exception.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw exception;
        } catch (IllegalArgumentException ex) {
            TokenException exception = new TokenException("JWT claims string is empty");
            exception.setHttpStatusCode(HttpStatus.BAD_REQUEST);
            throw exception;
        }
    }
}
