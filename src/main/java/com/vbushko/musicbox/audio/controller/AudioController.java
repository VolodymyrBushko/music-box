package com.vbushko.musicbox.audio.controller;

import com.vbushko.musicbox.audio.dto.AudioResponseDto;
import com.vbushko.musicbox.audio.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping
    public List<AudioResponseDto> findAllByNameLike(@RequestParam("audioName") final String name, final Pageable pageable) {
        return audioService.findAllByNameLike(name, pageable);
    }

    @DeleteMapping("/{audioName}")
    public void removeByName(@PathVariable final String audioName) {
        audioService.removeByName(audioName);
    }
}
