package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.PhotoRepository;
import com.wchallange.jsonplaceholder.service.PhotosService;
import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;
import com.wchallange.jsonplaceholder.service.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PhotosServiceImpl implements PhotosService {

    private final String PHOTO_API = Url.API.getUrl().concat(Url.PHOTOS.getUrl());
    private final RestTemplate restTemplate;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    public PhotosServiceImpl(RestTemplate restTemplate, AlbumRepository albumRepository, PhotoRepository photoRepository) {
        this.restTemplate = restTemplate;
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }


    @Override
    public PhotosDTO consumePhoto(Long id) {
        String url = PHOTO_API + "/{id}";
        PhotosDTO dto = restTemplate.getForObject(url, PhotosDTO.class, id);
        PhotosDTO toBeReturned = new PhotosDTO();
        Optional<Albums> albumFound = albumRepository.findById(dto.getAlbumId());
        if (albumFound.isPresent()) {
            Photos photo = PhotoMapper.toPhotosEntity(dto);
            photo.setAlbum(albumFound.get());
            Optional<Photos> photoFound = photoRepository.findById(photo.getId());
            if (photoFound.isPresent()) {
                if (!photoFound.get().equals(photo)) {
                    Photos result = photoRepository.save(photo);
                    toBeReturned = PhotoMapper.toPhotoDto(result);
                } else {
                    toBeReturned = PhotoMapper.toPhotoDto(photoFound.get());
                }
            } else {
                Photos result = photoRepository.save(photo);
                toBeReturned = PhotoMapper.toPhotoDto(result);
            }
        }
        return toBeReturned;
    }

    @Override
    public List<PhotosDTO> consumeAllPhotos() {
        PhotosDTO[] photosDtoList = restTemplate.getForObject(PHOTO_API, PhotosDTO[].class);
        List<PhotosDTO> photoList = Arrays.asList(photosDtoList);
        List<PhotosDTO> photoToBeReturned = new ArrayList<>();
        photoList.forEach(photoDto -> {
            Optional<Albums> albumsFound = albumRepository.findById(photoDto.getAlbumId());
            if (albumsFound.isPresent()) {
                Photos photo = PhotoMapper.toPhotosEntity(photoDto);
                photo.setAlbum(albumsFound.get());
                Optional<Photos> photoFound = photoRepository.findById(photo.getId());
                if (photoFound.isPresent()) {
                    if (!photoFound.get().equals(photo)) {
                        Photos result = photoRepository.save(photo);
                        photoToBeReturned.add(PhotoMapper.toPhotoDto(result));
                    } else {
                        photoToBeReturned.add(PhotoMapper.toPhotoDto(photo));
                    }
                } else {
                    Photos result = photoRepository.save(photo);
                    photoToBeReturned.add(PhotoMapper.toPhotoDto(result));
                }
            }
        });
        return photoToBeReturned;
    }

    @Override
    public List<PhotosDTO> findMyPictures(Long userId) {
        List<Albums> albumsFound = albumRepository.findByUserId(userId);
        List<PhotosDTO> photosToReturn = new ArrayList<>();
        albumsFound.forEach(albums -> {
            List<Photos> photosList = photoRepository.findByAlbum(albums);
            photosList.forEach(photos -> {
                photosToReturn.add(PhotoMapper.toPhotoDto(photos));
            });
        });
        return photosToReturn;
    }
}
