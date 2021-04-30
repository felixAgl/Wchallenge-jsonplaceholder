package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.PhotoRepository;
import com.wchallange.jsonplaceholder.service.PhotosService;
import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PhotosServiceImpl implements PhotosService {

    private final String PHOTO_API = Url.API.getUrl().concat(Url.PHOTOS.getUrl());
    private final RestTemplate restTemplate;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public PhotosServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PhotosDTO consumePhoto(Long id) {
        String url = PHOTO_API + "/{id}";
        return restTemplate.getForObject(url, PhotosDTO.class, id);
    }

    @Override
    public List<PhotosDTO> consumeAllPhotos() {
        PhotosDTO[] forObject = restTemplate.getForObject(PHOTO_API, PhotosDTO[].class);
        return Arrays.asList(forObject);
    }

    @Override
    public List<Photos> findMyPictures(Long userId) {
        List<Albums> albumsFound = albumRepository.findByUserId(userId);
        List<Photos> photosToReturn = new ArrayList<>();
        albumsFound.forEach(albums -> {
            List<Photos> photosList = photoRepository.findByAlbum(albums);
            photosList.forEach(photos -> {
                photosToReturn.add(photos);
            });
        });
        return photosToReturn;
    }
}
