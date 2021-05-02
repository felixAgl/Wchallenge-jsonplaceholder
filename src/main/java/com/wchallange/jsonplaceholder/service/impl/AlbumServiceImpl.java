package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.UserRepository;
import com.wchallange.jsonplaceholder.service.AlbumService;
import com.wchallange.jsonplaceholder.service.dto.AlbumDTO;
import com.wchallange.jsonplaceholder.service.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final String ALBUM_API = Url.API.getUrl().concat(Url.ALBUMS.getUrl());
    private final RestTemplate restTemplate;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AlbumServiceImpl(RestTemplate restTemplate, AlbumRepository albumRepository, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AlbumDTO consumeAlbum(Long id) {
        String url = ALBUM_API + "/{id}";
        AlbumDTO dto = restTemplate.getForObject(url, AlbumDTO.class, id);
        AlbumDTO toBeReturned = new AlbumDTO();
        Optional<Users> userFound = userRepository.findById(dto.getUserId());
        if (userFound.isPresent()) {
            Albums album = AlbumMapper.toAlbumEntity(dto);
            album.setUser(userFound.get());
            Optional<Albums> albumFound = albumRepository.findById(album.getId());
            if (albumFound.isPresent()) {
                if (!albumFound.get().equals(album)) {
                    Albums result = albumRepository.save(album);
                    toBeReturned = AlbumMapper.toAlbumDto(result);
                } else {
                    toBeReturned = AlbumMapper.toAlbumDto(albumFound.get());
                }
            } else {
                Albums result = albumRepository.save(album);
                toBeReturned = AlbumMapper.toAlbumDto(result);
            }
        }
        return toBeReturned;

    }

    @Override
    public List<AlbumDTO> consumeAllAlbums() {
        AlbumDTO[] albumDtoList = restTemplate.getForObject(ALBUM_API, AlbumDTO[].class);
        List<AlbumDTO> albumList = Arrays.asList(albumDtoList);
        List<AlbumDTO> albumToBeReturned = new ArrayList<>();
        albumList.forEach(albumDto -> {
            Optional<Users> userFound = userRepository.findById(albumDto.getUserId());
            if (userFound.isPresent()) {
                Albums album = AlbumMapper.toAlbumEntity(albumDto);
                album.setUser(userFound.get());
                Optional<Albums> albumFound = albumRepository.findById(album.getId());
                if (albumFound.isPresent()) {
                    if (!albumFound.get().equals(album)) {
                        Albums result = albumRepository.save(album);
                        albumToBeReturned.add(AlbumMapper.toAlbumDto(result));
                    } else {
                        albumToBeReturned.add(AlbumMapper.toAlbumDto(album));
                    }
                } else {
                    Albums result = albumRepository.save(album);
                    albumToBeReturned.add(AlbumMapper.toAlbumDto(result));
                }
            }
        });
        return albumToBeReturned;
    }
}
