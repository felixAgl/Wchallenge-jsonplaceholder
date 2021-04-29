package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.dto.AlbumDTO;

import java.util.List;

public interface AlbumService {

    AlbumDTO consumeAlbum(Long id);

    List<AlbumDTO> consumeAllAlbums();
}
