package com.wchallange.jsonplaceholder.dto;

import lombok.Data;

@Data
public class PhotosDTO {

    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;


}
