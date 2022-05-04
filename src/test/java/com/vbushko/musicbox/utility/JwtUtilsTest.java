package com.vbushko.musicbox.utility;

import com.vbushko.musicbox.common.utility.JwtUtils;
import com.vbushko.musicbox.exception.InvalidJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JwtUtils.class)
class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void shouldReturnJwtWhenGenerateJwtMethodIsCalled() {
        String jwt = jwtUtils.generateJwt("Bob");

        assertThat(jwt).isNotBlank();
    }

    @Test
    void shouldReturnTrueWhenIsJwtValidIsCalled() {
        String jwt = jwtUtils.generateJwt("Bob");

        assertTrue(jwtUtils.isJwtValid(jwt));
    }

    @Test
    void shouldReturnFalseWhenIsJwtValidIsCalled() {
        String invalidJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        assertFalse(jwtUtils.isJwtValid(invalidJwt));
    }

    @Test
    void shouldReturnBobWhenExtractUsernameFromJwtIsCalled() {
        String jwt = jwtUtils.generateJwt("Bob");

        String username = jwtUtils.extractUsernameFromJwt(jwt);

        assertThat(username).isEqualTo("Bob");
    }

    @Test
    void shouldThrowInvalidJwtExceptionWhenExtractUsernameFromJwtIsCalled() {
        String invalidJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        InvalidJwtException exception = assertThrows(InvalidJwtException.class,
                () -> jwtUtils.extractUsernameFromJwt(invalidJwt));

        assertTrue(exception.getMessage().contains("JWT validity cannot be asserted and should not be trusted"));
    }
}
