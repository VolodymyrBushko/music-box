package com.vbushko.musicbox.audio.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudioResponseDto implements Serializable {

    private long id;
    private String name;
    private String url;
}
