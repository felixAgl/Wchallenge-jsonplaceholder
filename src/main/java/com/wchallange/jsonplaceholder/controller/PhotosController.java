package com.wchallange.jsonplaceholder.controller;

import com.wchallange.jsonplaceholder.dto.PhotosDTO;
import com.wchallange.jsonplaceholder.service.PhotosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/photos/{id}")
    public Object consumePhoto(@PathVariable Long id) {
        try {
            PhotosDTO photosDTO = photosService.consumePhoto(id);
            return photosDTO;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/photos")
    public PhotosDTO[] consumeAllPhotos() {
        return photosService.consumeAllPhotos();

    }


}
