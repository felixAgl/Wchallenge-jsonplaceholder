package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;

import java.util.List;

public interface PhotosService {
    PhotosDTO consumePhoto(Long id);

    List<PhotosDTO> consumeAllPhotos();

    List<Photos> findMyPictures(Long userId);
}
