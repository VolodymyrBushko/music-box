package com.vbushko.musicbox.audio.controller;

import com.vbushko.musicbox.audio.dto.AudioRequestDto;
import com.vbushko.musicbox.audio.dto.AudioResponseDto;
import com.vbushko.musicbox.audio.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/audios")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AudioResponseDto saveAndUpload(@RequestParam("audio") final MultipartFile request) {
        return audioService.saveAndUpload(request);
    }
}
