package com.wchallange.jsonplaceholder.service.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
