package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.dto.PhotosDTO;

import java.util.List;

public interface PhotosService {
    PhotosDTO consumePhoto(Long id);

    List<PhotosDTO> consumeAllPhotos();
}
