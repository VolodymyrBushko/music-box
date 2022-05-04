package com.vbushko.musicbox.audio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudioResponseDto implements Serializable {

    private long id;
    private String name;
    private String url;
}
