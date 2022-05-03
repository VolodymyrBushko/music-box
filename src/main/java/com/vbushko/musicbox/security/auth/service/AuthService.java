package com.vbushko.musicbox.security.auth.service;

import com.vbushko.musicbox.common.utility.JwtUtils;
import com.vbushko.musicbox.exception.EntityAlreadyExistsException;
import com.vbushko.musicbox.exception.InvalidCredentialsException;
import com.vbushko.musicbox.security.auth.dto.SignInRequestDto;
import com.vbushko.musicbox.security.auth.dto.SignInResponseDto;
import com.vbushko.musicbox.security.auth.dto.SignUpRequestDto;
import com.vbushko.musicbox.security.auth.dto.SignUpResponseDto;
import com.vbushko.musicbox.security.auth.mapper.SignInMapper;
import com.vbushko.musicbox.security.auth.mapper.SignUpMapper;
import com.vbushko.musicbox.user.entity.User;
import com.vbushko.musicbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SignUpMapper signUpMapper;
    private final SignInMapper signInMapper;
    private final JwtUtils jwtUtils;

    @Transactional(propagation = Propagation.REQUIRED)
    public SignUpResponseDto signUp(final SignUpRequestDto request) {
        User user = Optional.of(request)
                .map(signUpMapper::map)
                .filter(e -> !userRepository.existsByUsername(e.getUsername()))
                .orElseThrow(() -> new EntityAlreadyExistsException(String.format("The user '%s' already exists", request.getUsername())));

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userRepository.saveAndFlush(user);
        log.info("The user '{}' has been signed up", request.getUsername());

        return signUpMapper.map(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SignInResponseDto signIn(final SignInRequestDto request) {
        String username = request.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        boolean passwordMatched = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (passwordMatched) {
            String token = jwtUtils.generateJwt(username);
            log.info("A token has been generated for '{}'", username);
            return signInMapper.map("Bearer " + token);
        }

        throw new InvalidCredentialsException("Invalid username or password");
    }
}
