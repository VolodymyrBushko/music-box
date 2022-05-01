package com.vbushko.musicbox.security.auth.mapper;

import com.vbushko.musicbox.security.auth.dto.SignInRequestDto;
import com.vbushko.musicbox.security.auth.dto.SignInResponseDto;
import com.vbushko.musicbox.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SignInMapper {

    public User map(SignInRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public SignInResponseDto map(String token) {
        return SignInResponseDto.builder()
                .token(token)
                .build();
    }
}
