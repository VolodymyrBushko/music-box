package com.vbushko.musicbox.utils;

import com.vbushko.musicbox.exception.InvalidJwsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwsUtilsTest {

    @Autowired
    private JwsUtils jwsUtils;

    @Test
    void shouldReturnJwsWhenGenerateJwsMethodIsCalled() {
        String jws = jwsUtils.generateJws("Bob");

        assertThat(jws).isNotBlank();
    }

    @Test
    void shouldReturnTrueWhenIsJwsValidIsCalled() {
        String jws = jwsUtils.generateJws("Bob");

        assertTrue(jwsUtils.isJwsValid(jws));
    }

    @Test
    void shouldReturnFalseWhenIsJwsValidIsCalled() {
        String invalidJws = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        assertFalse(jwsUtils.isJwsValid(invalidJws));
    }

    @Test
    void shouldReturnBobWhenExtractUsernameFromJwsIsCalled() {
        String jws = jwsUtils.generateJws("Bob");

        String username = jwsUtils.extractUsernameFromJws(jws);

        assertThat(username).isEqualTo("Bob");
    }

    @Test
    void shouldThrowInvalidJwsExceptionWhenExtractUsernameFromJwsIsCalled() {
        String invalidJws = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        InvalidJwsException exception = assertThrows(InvalidJwsException.class,
                () -> jwsUtils.extractUsernameFromJws(invalidJws));

        assertTrue(exception.getMessage().contains("JWT validity cannot be asserted and should not be trusted"));
    }
}
