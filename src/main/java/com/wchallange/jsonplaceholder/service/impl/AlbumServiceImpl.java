package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.service.dto.AlbumDTO;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.service.AlbumService;
import com.wchallange.jsonplaceholder.service.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final String ALBUM_API = Url.API.getUrl().concat(Url.ALBUMS.getUrl());
    private final RestTemplate restTemplate;

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AlbumDTO consumeAlbum(Long id) {
        String url = ALBUM_API + "/{id}";
        AlbumDTO dto = restTemplate.getForObject(url, AlbumDTO.class, id);
        Albums albums = AlbumMapper.toAlbumEntity(dto);
        Optional<Albums> albumFound = albumRepository.findById(dto.getId());
        AlbumDTO toBeReturned = new AlbumDTO();
        if (albumFound.isPresent()){
            if (!albumFound.get().equals(albums)){
//                albums.setUser(albumFound.get().getUser());
                Albums result = albumRepository.save(albums);
                toBeReturned = AlbumMapper.toAlbumDto(result);
            }else {
                toBeReturned = AlbumMapper.toAlbumDto(albumFound.get());
            }
        }else {
            Albums result = albumRepository.save(albums);
            toBeReturned = AlbumMapper.toAlbumDto(result);
        }
        return toBeReturned;

    }

    @Override
    public List<AlbumDTO> consumeAllAlbums() {
        AlbumDTO[] forObject = restTemplate.getForObject(ALBUM_API, AlbumDTO[].class);
        return Arrays.asList(forObject);
    }
}
