package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Albums, Long> {

    Optional<Albums> findByIdAndUserId(Long album, Long id);

    List<Albums> findByUserId(Long userId);
}
