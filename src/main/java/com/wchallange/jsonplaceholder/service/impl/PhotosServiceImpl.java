package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.dto.PhotosDTO;
import com.wchallange.jsonplaceholder.enumeration.Url;
import com.wchallange.jsonplaceholder.service.PhotosService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotosServiceImpl implements PhotosService {

    private final String PHOTO_API = Url.URL.getUrl().concat(Url.PHOTOS.getUrl());
    private final RestTemplate restTemplate;

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
}
