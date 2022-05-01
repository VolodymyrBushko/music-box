package com.vbushko.musicbox.security.userdetails.service;

import com.vbushko.musicbox.exception.InvalidCredentialsException;
import com.vbushko.musicbox.security.userdetails.model.CustomUserDetails;
import com.vbushko.musicbox.user.entity.User;
import com.vbushko.musicbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
