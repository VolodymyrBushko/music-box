package com.vbushko.musicbox.auth.mapper;

import com.vbushko.musicbox.auth.dto.SignUpRequestDto;
import com.vbushko.musicbox.auth.dto.SignUpResponseDto;
import com.vbushko.musicbox.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SignUpMapper {

    public User map(final SignUpRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public SignUpResponseDto map(final User user) {
        return SignUpResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
