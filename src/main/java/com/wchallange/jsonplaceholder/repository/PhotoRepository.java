package com.wchallange.jsonplaceholder.repository;


import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photos, Long> {

    List<Photos> findByAlbum(Albums albums);
}
