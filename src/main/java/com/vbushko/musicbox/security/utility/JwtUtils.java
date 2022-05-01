package com.vbushko.musicbox.security.utility;

import com.vbushko.musicbox.exception.InvalidJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time-in-seconds}")
    private int expirationTimeInSeconds;

    private SecretKey keyToSignWith;

    @PostConstruct
    public void postConstruct() {
        keyToSignWith = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwt(final String username) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(DateUtils.addSeconds(now, expirationTimeInSeconds))
                .setId(UUID.randomUUID().toString())
                .signWith(keyToSignWith, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsernameFromJwt(final String jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(keyToSignWith)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new InvalidJwtException(e.getMessage(), e.getCause());
        }
    }

    public boolean isJwtValid(final String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(keyToSignWith)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
