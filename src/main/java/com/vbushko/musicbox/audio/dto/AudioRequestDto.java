package com.vbushko.musicbox.audio.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudioRequestDto implements Serializable {

    private String name;
    private MultipartFile audio;
}
