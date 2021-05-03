package com.wchallange.jsonplaceholder.web.rest;

import com.wchallange.jsonplaceholder.service.AlbumService;
import com.wchallange.jsonplaceholder.service.dto.AlbumDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Object> consumeAlbum(@PathVariable Long id) {
        try {
            AlbumDTO albumDTO = albumService.consumeAlbum(id);
            return new ResponseEntity<>(albumDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDTO>> consumeAllAlbums() {
        return new ResponseEntity<>(albumService.consumeAllAlbums(), HttpStatus.OK);

    }
}
