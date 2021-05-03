package com.wchallange.jsonplaceholder.service.mapper;

import com.wchallange.jsonplaceholder.domain.Comments;
import com.wchallange.jsonplaceholder.service.dto.CommentDTO;

public class CommentMapper {

    public static CommentDTO toCommentDto(Comments comments) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comments.getId());
        commentDTO.setName(comments.getName());
        commentDTO.setBody(comments.getBody());
        commentDTO.setEmail(comments.getEmail());
        commentDTO.setPostId(comments.getPost().getId());
        return commentDTO;
    }

    public static Comments toCommentsEntity(CommentDTO commentDTO) {
        Comments comments = new Comments();
        comments.setId(commentDTO.getId());
        comments.setName(commentDTO.getName());
        comments.setEmail(commentDTO.getEmail());
        comments.setBody(commentDTO.getBody());
        return comments;
    }
}
