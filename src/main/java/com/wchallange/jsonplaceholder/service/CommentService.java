package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO consumeComment(Long id);

    List<CommentDTO> consumeAllComments();

    List<CommentDTO> consumeAllCommentsByUserId(Long userId);
}
