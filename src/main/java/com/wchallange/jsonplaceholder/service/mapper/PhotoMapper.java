package com.wchallange.jsonplaceholder.service.mapper;

import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;

public class PhotoMapper {

    public static PhotosDTO toPhotoDto(Photos photo) {
        PhotosDTO dto = new PhotosDTO();
        dto.setId(photo.getId());
        dto.setThumbnailUrl(photo.getThumbnailUrl());
        dto.setTitle(photo.getTitle());
        dto.setUrl(photo.getUrl());
        dto.setAlbumId(photo.getAlbum().getId());
        return dto;
    }

    public static Photos toPhotosEntity(PhotosDTO dto) {
        Photos photos = new Photos();
        photos.setId(dto.getId());
        photos.setTitle(dto.getTitle());
        photos.setThumbnailUrl(dto.getThumbnailUrl());
        photos.setUrl(dto.getUrl());
        return photos;
    }
}
