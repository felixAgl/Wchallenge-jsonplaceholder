package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Posts;
import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.repository.PostRepository;
import com.wchallange.jsonplaceholder.repository.UserRepository;
import com.wchallange.jsonplaceholder.service.PostService;
import com.wchallange.jsonplaceholder.service.dto.PostDTO;
import com.wchallange.jsonplaceholder.service.mapper.PostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final String POST_API = Url.API.getUrl().concat(Url.POSTS.getUrl());

    private final RestTemplate restTemplate;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(RestTemplate restTemplate, PostRepository postRepository, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDTO consumePost(Long id) {
        String url = POST_API + "/{id}";
        PostDTO dto = restTemplate.getForObject(url, PostDTO.class, id);
        PostDTO toBeReturned = new PostDTO();
        Optional<Users> userFound = userRepository.findById(dto.getUserId());
        if (userFound.isPresent()) {
            Posts post = PostsMapper.toPostEntity(dto);
            post.setUser(userFound.get());
            Optional<Posts> albumFound = postRepository.findById(post.getId());
            if (albumFound.isPresent()) {
                if (!albumFound.get().equals(post)) {
                    Posts result = postRepository.save(post);
                    toBeReturned = PostsMapper.toPostDto(result);
                } else {
                    toBeReturned = PostsMapper.toPostDto(albumFound.get());
                }
            } else {
                Posts result = postRepository.save(post);
                toBeReturned = PostsMapper.toPostDto(result);
            }
        }
        return toBeReturned;

    }

    @Override
    public List<PostDTO> consumeAllPosts() {
        PostDTO[] postDtoList = restTemplate.getForObject(POST_API, PostDTO[].class);
        List<PostDTO> postList = Arrays.asList(postDtoList);
        List<PostDTO> postToBeReturned = new ArrayList<>();
        postList.forEach(albumDto -> {
            Optional<Users> userFound = userRepository.findById(albumDto.getUserId());
            if (userFound.isPresent()) {
                Posts post = PostsMapper.toPostEntity(albumDto);
                post.setUser(userFound.get());
                Optional<Posts> albumFound = postRepository.findById(post.getId());
                if (albumFound.isPresent()) {
                    if (!albumFound.get().equals(post)) {
                        Posts result = postRepository.save(post);
                        postToBeReturned.add(PostsMapper.toPostDto(result));
                    } else {
                        postToBeReturned.add(PostsMapper.toPostDto(post));
                    }
                } else {
                    Posts result = postRepository.save(post);
                    postToBeReturned.add(PostsMapper.toPostDto(result));
                }
            }
        });
        return postToBeReturned;
    }
}
