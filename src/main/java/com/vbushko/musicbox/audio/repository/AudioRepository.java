package com.vbushko.musicbox.audio.repository;

import com.vbushko.musicbox.audio.entity.Audio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {

    boolean existsByName(String name);

    Optional<Audio> findByName(String name);

    void deleteByName(String name);

    @Query("SELECT a FROM Audio a WHERE a.name LIKE %:name%")
    Page<Audio> findAllByNameLike(@Param("name") String name, Pageable pageable);
}
