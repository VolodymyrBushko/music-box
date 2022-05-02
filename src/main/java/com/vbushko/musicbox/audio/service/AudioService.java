package com.vbushko.musicbox.audio.service;

import com.vbushko.musicbox.audio.dto.AudioResponseDto;
import com.vbushko.musicbox.audio.entity.Audio;
import com.vbushko.musicbox.audio.mapper.AudioMapper;
import com.vbushko.musicbox.audio.repository.AudioRepository;
import com.vbushko.musicbox.exception.AudioAlreadyExistsException;
import com.vbushko.musicbox.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepository audioRepository;
    private final AudioMapper audioMapper;
    private final StorageService storageService;

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED)
    public AudioResponseDto saveAndUpload(final MultipartFile request) {
        String audioName = request.getOriginalFilename();
        InputStream audioData = request.getInputStream();

        Audio audio = Optional.of(request)
                .map(audioMapper::map)
                .filter(e -> !audioRepository.existsByName(e.getName()))
                .orElseThrow(() -> new AudioAlreadyExistsException(String.format("The audio '%s' already exists", audioName)));

        audioRepository.save(audio);
        storageService.uploadBlob(audioName, audioData);
        log.info("An audio '{}' has been saved successfully", audio.getName());

        return audioMapper.map(audio);
    }
}
