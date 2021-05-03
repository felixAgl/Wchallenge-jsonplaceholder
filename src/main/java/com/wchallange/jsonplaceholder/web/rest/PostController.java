package com.wchallange.jsonplaceholder.web.rest;

import com.wchallange.jsonplaceholder.service.PostService;
import com.wchallange.jsonplaceholder.service.dto.PostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> consumePost(@PathVariable Long id) {
        try {
            PostDTO postDTO = postService.consumePost(id);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> consumeAllPosts() {
        return new ResponseEntity<>(postService.consumeAllPosts(), HttpStatus.OK);

    }
}
