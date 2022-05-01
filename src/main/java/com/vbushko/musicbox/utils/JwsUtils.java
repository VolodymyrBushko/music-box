package com.vbushko.musicbox.utils;

import com.vbushko.musicbox.exception.InvalidJwsException;
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
public class JwsUtils {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time-in-seconds}")
    private int expirationTimeInSeconds;

    private SecretKey keyToSignWith;

    @PostConstruct
    public void postConstruct() {
        keyToSignWith = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJws(final String username) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(DateUtils.addSeconds(now, expirationTimeInSeconds))
                .setId(UUID.randomUUID().toString())
                .signWith(keyToSignWith, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsernameFromJws(final String jws) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(keyToSignWith)
                    .build()
                    .parseClaimsJws(jws)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new InvalidJwsException(e.getMessage(), e.getCause());
        }
    }

    public boolean isJwsValid(final String jws) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(keyToSignWith)
                    .build()
                    .parseClaimsJws(jws);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
