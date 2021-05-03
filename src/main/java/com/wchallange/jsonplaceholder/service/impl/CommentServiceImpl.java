package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Comments;
import com.wchallange.jsonplaceholder.domain.Posts;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.repository.CommentRepository;
import com.wchallange.jsonplaceholder.repository.PostRepository;
import com.wchallange.jsonplaceholder.service.CommentService;
import com.wchallange.jsonplaceholder.service.dto.CommentDTO;
import com.wchallange.jsonplaceholder.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final String COMMENT_API = Url.API.getUrl().concat(Url.COMMENTS.getUrl());
    private final RestTemplate restTemplate;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(RestTemplate restTemplate, CommentRepository commentRepository, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO consumeComment(Long id) {
        String url = COMMENT_API + "/{id}";
        CommentDTO dto = restTemplate.getForObject(url, CommentDTO.class, id);
        CommentDTO toBeReturned = new CommentDTO();
        Optional<Posts> postFound = postRepository.findById(dto.getPostId());
        if (postFound.isPresent()) {
            Comments comment = CommentMapper.toCommentsEntity(dto);
            comment.setPost(postFound.get());
            Optional<Comments> commetFound = commentRepository.findById(comment.getId());
            if (commetFound.isPresent()) {
                if (!commetFound.get().equals(comment)) {
                    Comments result = commentRepository.save(comment);
                    toBeReturned = CommentMapper.toCommentDto(result);
                } else {
                    toBeReturned = CommentMapper.toCommentDto(commetFound.get());
                }
            } else {
                Comments result = commentRepository.save(comment);
                toBeReturned = CommentMapper.toCommentDto(result);
            }
        }
        return toBeReturned;
    }

    @Override
    public List<CommentDTO> consumeAllComments() {
        CommentDTO[] commentDtoList = restTemplate.getForObject(COMMENT_API, CommentDTO[].class);
        List<CommentDTO> commnetList = Arrays.asList(commentDtoList);
        List<CommentDTO> commentToBeReturned = new ArrayList<>();
        commnetList.forEach(commentDto -> {
            Optional<Posts> postFound = postRepository.findById(commentDto.getPostId());
            if (postFound.isPresent()) {
                Comments comment = CommentMapper.toCommentsEntity(commentDto);
                comment.setPost(postFound.get());
                Optional<Comments> commentFound = commentRepository.findById(comment.getId());
                if (commentFound.isPresent()) {
                    if (!commentFound.get().equals(comment)) {
                        Comments result = commentRepository.save(comment);
                        commentToBeReturned.add(CommentMapper.toCommentDto(result));
                    } else {
                        commentToBeReturned.add(CommentMapper.toCommentDto(comment));
                    }
                } else {
                    Comments result = commentRepository.save(comment);
                    commentToBeReturned.add(CommentMapper.toCommentDto(result));
                }
            }
        });
        return commentToBeReturned;
    }
}
