package com.vbushko.musicbox.audio.repository;

import com.vbushko.musicbox.audio.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {

    boolean existsByName(String name);
}
