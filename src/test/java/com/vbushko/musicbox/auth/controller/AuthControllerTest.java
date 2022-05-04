package com.vbushko.musicbox.auth.controller;

import com.vbushko.musicbox.auth.dto.SignInRequestDto;
import com.vbushko.musicbox.auth.dto.SignInResponseDto;
import com.vbushko.musicbox.auth.dto.SignUpRequestDto;
import com.vbushko.musicbox.auth.dto.SignUpResponseDto;
import com.vbushko.musicbox.auth.service.AuthService;
import com.vbushko.musicbox.common.utility.JwtUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.vbushko.musicbox.common.utility.JsonUtils.fromValueToJson;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtUtils jwtUtils;

    @Test
    @SneakyThrows
    void shouldSignUpUserByUsernameAndPassword() {
        var request = SignUpRequestDto.builder()
                .username("John")
                .password("1234")
                .build();

        var response = SignUpResponseDto.builder()
                .id(1L)
                .username("John")
                .password("1234")
                .build();

        when(authService.signUp(request)).thenReturn(response);

        mvc.perform(post("/api/v1/auth/sign-up")
                        .content(fromValueToJson(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("John"))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void shouldReturnAccessTokenByUsernameAndPassword() {
        var request = SignInRequestDto.builder()
                .username("John")
                .password("1234")
                .build();

        var response = SignInResponseDto.builder()
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2b2xvZHlteXIiLCJpYXQiOjE2NTE1NjE1NjQsImV4cCI6MTY1MTU2NTE2NCwianRpIjoiYjkwZDRjOTktNjE0My00NWM5LWJhN2YtNTg2NzBkNDg1MjljIn0.JDytZnVtnG2y4dFN2VhnfUZb1KTyYJ7jV62M5V6Oby8")
                .build();

        when(authService.signIn(request)).thenReturn(response);

        mvc.perform(post("/api/v1/auth/sign-in")
                        .content(fromValueToJson(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2b2xvZHlteXIiLCJpYXQiOjE2NTE1NjE1NjQsImV4cCI6MTY1MTU2NTE2NCwianRpIjoiYjkwZDRjOTktNjE0My00NWM5LWJhN2YtNTg2NzBkNDg1MjljIn0.JDytZnVtnG2y4dFN2VhnfUZb1KTyYJ7jV62M5V6Oby8"))
                .andDo(print());
    }
}
