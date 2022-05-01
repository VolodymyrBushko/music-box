package com.vbushko.musicbox.user.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {

    private String username;
    private String password;
}
