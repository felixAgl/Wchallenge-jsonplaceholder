package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.dto.PhotosDTO;

public interface PhotosService {
    PhotosDTO consumePhoto(Long id);

    PhotosDTO[] consumeAllPhotos();
}
