package com.wchallange.jsonplaceholder.web.rest;

import com.wchallange.jsonplaceholder.service.CommentService;
import com.wchallange.jsonplaceholder.service.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Object> consumeAlbum(@PathVariable Long id) {
        try {
            CommentDTO commentDTO = commentService.consumeComment(id);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> consumeAllAlbums() {
        return new ResponseEntity<>(commentService.consumeAllComments(), HttpStatus.OK);

    }
}
