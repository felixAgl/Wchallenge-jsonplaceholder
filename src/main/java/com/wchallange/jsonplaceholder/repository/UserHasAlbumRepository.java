package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.UserHasAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHasAlbumRepository extends JpaRepository<UserHasAlbum, Long > {

    Optional<UserHasAlbum> findByUserIdAndAlbumId(Long userId, Long albumId);
}
