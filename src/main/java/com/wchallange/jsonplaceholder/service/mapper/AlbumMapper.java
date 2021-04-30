package com.wchallange.jsonplaceholder.service.mapper;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.service.dto.AlbumDTO;

public class AlbumMapper {


    public static AlbumDTO toAlbumDto(Albums album){
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setUserId(album.getUser().getId());
        return dto;
    }

    public static Albums toAlbumEntity(AlbumDTO dto){
        Albums albums = new Albums();
        albums.setId(dto.getId());
        albums.setTitle(dto.getTitle());
        return  albums;
    }

}
