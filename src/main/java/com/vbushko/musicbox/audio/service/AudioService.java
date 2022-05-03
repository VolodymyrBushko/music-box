package com.vbushko.musicbox.audio.service;

import com.vbushko.musicbox.audio.dto.AudioResponseDto;
import com.vbushko.musicbox.audio.entity.Audio;
import com.vbushko.musicbox.audio.mapper.AudioMapper;
import com.vbushko.musicbox.audio.repository.AudioRepository;
import com.vbushko.musicbox.common.utility.SecurityContextUtils;
import com.vbushko.musicbox.exception.EntityAlreadyExistsException;
import com.vbushko.musicbox.exception.EntityDoesNotExistException;
import com.vbushko.musicbox.exception.PermissionDeniedException;
import com.vbushko.musicbox.storage.service.BlobStorageService;
import com.vbushko.musicbox.user.entity.User;
import com.vbushko.musicbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepository audioRepository;
    private final AudioMapper audioMapper;
    private final UserService userService;
    private final SecurityContextUtils securityContextUtils;
    private final BlobStorageService blobStorageService;

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED)
    public AudioResponseDto saveAndUpload(final MultipartFile audioRequest) {
        String audioName = audioRequest.getOriginalFilename();
        InputStream audioData = audioRequest.getInputStream();

        Audio audio = Optional.of(audioRequest)
                .map(audioMapper::map)
                .filter(e -> !audioRepository.existsByName(e.getName()))
                .orElseThrow(() -> new EntityAlreadyExistsException(String.format("The audio '%s' already exists", audioName)));

        User currentUser = userService.findByUsername(securityContextUtils.getUsername());
        audio.setPublisher(currentUser);

        audioRepository.save(audio);
        blobStorageService.uploadBlob(audioName, audioData);
        log.info("The audio '{}' has been saved successfully", audioName);

        return audioMapper.map(audio);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AudioResponseDto> findAllByNameLike(final String audioName, final Pageable pageable) {
        return audioRepository.findAllByNameLike(audioName, pageable).stream()
                .map(audioMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeByName(final String audioName) {
        Audio audio = audioRepository.findByName(audioName)
                .orElseThrow(() -> new EntityDoesNotExistException(String.format("The audio '%s' doesn't exist", audioName)));

        String publisherUsername = audio.getPublisher().getUsername();
        String currentUsername = securityContextUtils.getUsername();

        if (publisherUsername.equals(currentUsername)) {
            audioRepository.deleteByName(audioName);
            blobStorageService.removeBlob(audioName);
            log.info("The audio '{}' has been removed successfully", audioName);
            return;
        }

        throw new PermissionDeniedException(String.format("You don't have permission to remove the following audio: '%s'", audioName));
    }
}
