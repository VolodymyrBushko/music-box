package com.vbushko.musicbox.userdetails.mapper;

import com.vbushko.musicbox.userdetails.model.CustomUserDetails;
import com.vbushko.musicbox.user.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsMapper {

    public CustomUserDetails map(final User user) {
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
