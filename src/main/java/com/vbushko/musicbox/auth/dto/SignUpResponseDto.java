package com.vbushko.musicbox.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto implements Serializable {

    private long id;
    private String username;
    private String password;
}
