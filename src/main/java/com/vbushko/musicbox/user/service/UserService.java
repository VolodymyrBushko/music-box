package com.vbushko.musicbox.user.service;

import com.vbushko.musicbox.exception.InvalidCredentialsException;
import com.vbushko.musicbox.user.entity.User;
import com.vbushko.musicbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User findByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
    }
}
