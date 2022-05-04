package com.vbushko.musicbox.auth.controller;

import com.vbushko.musicbox.auth.dto.SignInRequestDto;
import com.vbushko.musicbox.auth.dto.SignInResponseDto;
import com.vbushko.musicbox.auth.dto.SignUpRequestDto;
import com.vbushko.musicbox.auth.dto.SignUpResponseDto;
import com.vbushko.musicbox.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponseDto signUp(@RequestBody final SignUpRequestDto request) {
        return authService.signUp(request);
    }

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody final SignInRequestDto request) {
        return authService.signIn(request);
    }
}
