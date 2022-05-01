package com.vbushko.musicbox.user.mapper;

import com.vbushko.musicbox.user.dto.UserRequestDto;
import com.vbushko.musicbox.user.dto.UserResponseDto;
import com.vbushko.musicbox.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public UserResponseDto map(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
