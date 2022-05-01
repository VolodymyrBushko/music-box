package com.vbushko.musicbox.security.auth.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto implements Serializable {

    private long id;
    private String username;
    private String password;
}
