package com.vbushko.musicbox.userdetails.service;

import com.vbushko.musicbox.userdetails.mapper.CustomUserDetailsMapper;
import com.vbushko.musicbox.user.entity.User;
import com.vbushko.musicbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final CustomUserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return userDetailsMapper.map(user);
    }
}
