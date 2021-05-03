package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.service.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO consumePost(Long id);

    List<PostDTO> consumeAllPosts();
}
