package com.vbushko.musicbox.auth.mapper;

import com.vbushko.musicbox.auth.dto.SignInRequestDto;
import com.vbushko.musicbox.auth.dto.SignInResponseDto;
import com.vbushko.musicbox.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SignInMapper {

    public User map(final SignInRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public SignInResponseDto map(final String token) {
        return SignInResponseDto.builder()
                .token(token)
                .build();
    }
}
