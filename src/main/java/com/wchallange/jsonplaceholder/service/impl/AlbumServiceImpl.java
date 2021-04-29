package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.dto.AlbumDTO;
import com.wchallange.jsonplaceholder.dto.UserDTO;
import com.wchallange.jsonplaceholder.enumeration.Url;
import com.wchallange.jsonplaceholder.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final String ALBUM_API = Url.URL.getUrl().concat(Url.ALBUMS.getUrl());
    private final RestTemplate restTemplate;

    public AlbumServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AlbumDTO consumeAlbum(Long id) {
        String url = ALBUM_API + "/{id}";
        return restTemplate.getForObject(url, AlbumDTO.class, id);
    }

    @Override
    public List<AlbumDTO> consumeAllAlbums() {
        AlbumDTO[] forObject = restTemplate.getForObject(ALBUM_API, AlbumDTO[].class);
        return Arrays.asList(forObject);
    }
}
