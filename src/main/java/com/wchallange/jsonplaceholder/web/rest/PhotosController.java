package com.wchallange.jsonplaceholder.web.rest;

import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;
import com.wchallange.jsonplaceholder.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Object> consumePhoto(@PathVariable Long id) {
        try {
            PhotosDTO photosDTO = photosService.consumePhoto(id);
            return new ResponseEntity<>(photosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/photos")
    public ResponseEntity<List<PhotosDTO>> consumeAllPhotos() {
        return new ResponseEntity<>(photosService.consumeAllPhotos(), HttpStatus.OK);
    }

}
