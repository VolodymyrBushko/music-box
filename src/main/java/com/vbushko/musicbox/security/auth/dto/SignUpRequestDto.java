package com.vbushko.musicbox.security.auth.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto implements Serializable {

    private String username;
    private String password;
}
