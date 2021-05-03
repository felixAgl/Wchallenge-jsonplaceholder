package com.wchallange.jsonplaceholder.service.mapper;

import com.wchallange.jsonplaceholder.domain.Posts;
import com.wchallange.jsonplaceholder.service.dto.PostDTO;

public class PostsMapper {

    public static PostDTO toPostDto(Posts posts) {
        PostDTO dto = new PostDTO();
        dto.setId(posts.getId());
        dto.setTitle(posts.getTitle());
        dto.setBody(posts.getBody());
        dto.setUserId(posts.getUser().getId());
        return dto;
    }

    public static Posts toPostEntity(PostDTO dto) {
        Posts posts = new Posts();
        posts.setId(dto.getId());
        posts.setTitle(dto.getTitle());
        posts.setBody(dto.getBody());
        return posts;
    }
}
