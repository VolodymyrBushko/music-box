package com.vbushko.musicbox.audio.mapper;

import com.vbushko.musicbox.audio.dto.AudioResponseDto;
import com.vbushko.musicbox.audio.entity.Audio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AudioMapper {

    @Value("${azure.blob-storage.containers.audios.url}")
    private String containerUrl;

    public Audio map(final MultipartFile multipartFile) {
        String audioName = multipartFile.getOriginalFilename();
        String audioUrl = String.format("%s/%s", containerUrl, audioName);

        return Audio.builder()
                .name(audioName)
                .url(audioUrl)
                .build();
    }

    public AudioResponseDto map(final Audio audio) {
        return AudioResponseDto.builder()
                .id(audio.getId())
                .name(audio.getName())
                .url(audio.getUrl())
                .build();
    }
}
