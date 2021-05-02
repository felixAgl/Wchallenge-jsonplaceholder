package com.wchallange.jsonplaceholder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.PhotoRepository;
import com.wchallange.jsonplaceholder.service.dto.PhotosDTO;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {PhotosServiceImpl.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
public class PhotosServiceImplTest {
    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private PhotoRepository photoRepository;

    @Autowired
    private PhotosServiceImpl photosServiceImpl;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testConsumePhoto() throws RestClientException {
        when(this.restTemplate.getForObject(anyString(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new PhotosDTO());

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setLat("Lat");
        users.setName("Name");
        users.setCompanyName("Company Name");
        users.setWebsite("Website");
        users.setCatchPhrase("Catch Phrase");
        users.setUsername("janedoe");
        users.setSuite("Suite");
        users.setCity("Oxford");
        users.setId(123L);
        users.setPhone("4105551212");
        users.setBs("Bs");
        users.setZipcode("21654");
        users.setLng("Lng");
        users.setStreet("Street");

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users);
        albums.setTitle("Dr");

        Photos photos = new Photos();
        photos.setId(123L);
        photos.setUrl("https://example.org/example");
        photos.setTitle("Dr");
        photos.setAlbum(albums);
        photos.setThumbnailUrl("https://example.org/example");
        Optional<Photos> ofResult = Optional.<Photos>of(photos);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setLat("Lat");
        users1.setName("Name");
        users1.setCompanyName("Company Name");
        users1.setWebsite("Website");
        users1.setCatchPhrase("Catch Phrase");
        users1.setUsername("janedoe");
        users1.setSuite("Suite");
        users1.setCity("Oxford");
        users1.setId(123L);
        users1.setPhone("4105551212");
        users1.setBs("Bs");
        users1.setZipcode("21654");
        users1.setLng("Lng");
        users1.setStreet("Street");

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users1);
        albums1.setTitle("Dr");

        Photos photos1 = new Photos();
        photos1.setId(123L);
        photos1.setUrl("https://example.org/example");
        photos1.setTitle("Dr");
        photos1.setAlbum(albums1);
        photos1.setThumbnailUrl("https://example.org/example");
        when(this.photoRepository.save((Photos) any())).thenReturn(photos1);
        when(this.photoRepository.findById((Long) any())).thenReturn(ofResult);

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setLat("Lat");
        users2.setName("Name");
        users2.setCompanyName("Company Name");
        users2.setWebsite("Website");
        users2.setCatchPhrase("Catch Phrase");
        users2.setUsername("janedoe");
        users2.setSuite("Suite");
        users2.setCity("Oxford");
        users2.setId(123L);
        users2.setPhone("4105551212");
        users2.setBs("Bs");
        users2.setZipcode("21654");
        users2.setLng("Lng");
        users2.setStreet("Street");

        Albums albums2 = new Albums();
        albums2.setId(123L);
        albums2.setUser(users2);
        albums2.setTitle("Dr");
        Optional<Albums> ofResult1 = Optional.<Albums>of(albums2);
        when(this.albumRepository.findById((Long) any())).thenReturn(ofResult1);
        PhotosDTO actualConsumePhotoResult = this.photosServiceImpl.consumePhoto(123L);
        assertEquals(123L, actualConsumePhotoResult.getAlbumId().longValue());
        assertEquals("https://example.org/example", actualConsumePhotoResult.getUrl());
        assertEquals("Dr", actualConsumePhotoResult.getTitle());
        assertEquals("https://example.org/example", actualConsumePhotoResult.getThumbnailUrl());
        assertEquals(123L, actualConsumePhotoResult.getId().longValue());
        verify(this.albumRepository).findById((Long) any());
        verify(this.photoRepository).save((Photos) any());
        verify(this.photoRepository).findById((Long) any());
        verify(this.restTemplate).getForObject(anyString(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    public void testFindMyPictures() {
        when(this.albumRepository.findByUserId((Long) any())).thenReturn(new ArrayList<Albums>());
        assertTrue(this.photosServiceImpl.findMyPictures(123L).isEmpty());
        verify(this.albumRepository).findByUserId((Long) any());
    }

    @Test
    public void testFindMyPictures2() {
        when(this.photoRepository.findByAlbum((Albums) any())).thenReturn(new ArrayList<Photos>());

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setLat("Lat");
        users.setName("Name");
        users.setCompanyName("Company Name");
        users.setWebsite("Website");
        users.setCatchPhrase("Catch Phrase");
        users.setUsername("janedoe");
        users.setSuite("Suite");
        users.setCity("Oxford");
        users.setId(123L);
        users.setPhone("4105551212");
        users.setBs("Bs");
        users.setZipcode("21654");
        users.setLng("Lng");
        users.setStreet("Street");

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users);
        albums.setTitle("Dr");

        ArrayList<Albums> albumsList = new ArrayList<Albums>();
        albumsList.add(albums);
        when(this.albumRepository.findByUserId((Long) any())).thenReturn(albumsList);
        assertTrue(this.photosServiceImpl.findMyPictures(123L).isEmpty());
        verify(this.albumRepository).findByUserId((Long) any());
        verify(this.photoRepository).findByAlbum((Albums) any());
    }

    @Test
    public void testFindMyPictures3() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setLat("Lat");
        users.setName("Name");
        users.setCompanyName("Company Name");
        users.setWebsite("Website");
        users.setCatchPhrase("Catch Phrase");
        users.setUsername("janedoe");
        users.setSuite("Suite");
        users.setCity("Oxford");
        users.setId(123L);
        users.setPhone("4105551212");
        users.setBs("Bs");
        users.setZipcode("21654");
        users.setLng("Lng");
        users.setStreet("Street");

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users);
        albums.setTitle("Dr");

        Photos photos = new Photos();
        photos.setId(123L);
        photos.setUrl("https://example.org/example");
        photos.setTitle("Dr");
        photos.setAlbum(albums);
        photos.setThumbnailUrl("https://example.org/example");

        ArrayList<Photos> photosList = new ArrayList<Photos>();
        photosList.add(photos);
        when(this.photoRepository.findByAlbum((Albums) any())).thenReturn(photosList);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setLat("Lat");
        users1.setName("Name");
        users1.setCompanyName("Company Name");
        users1.setWebsite("Website");
        users1.setCatchPhrase("Catch Phrase");
        users1.setUsername("janedoe");
        users1.setSuite("Suite");
        users1.setCity("Oxford");
        users1.setId(123L);
        users1.setPhone("4105551212");
        users1.setBs("Bs");
        users1.setZipcode("21654");
        users1.setLng("Lng");
        users1.setStreet("Street");

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users1);
        albums1.setTitle("Dr");

        ArrayList<Albums> albumsList = new ArrayList<Albums>();
        albumsList.add(albums1);
        when(this.albumRepository.findByUserId((Long) any())).thenReturn(albumsList);
        assertEquals(1, this.photosServiceImpl.findMyPictures(123L).size());
        verify(this.albumRepository).findByUserId((Long) any());
        verify(this.photoRepository).findByAlbum((Albums) any());
    }

    @Test
    public void testFindMyPictures4() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setLat("Lat");
        users.setName("Name");
        users.setCompanyName("Company Name");
        users.setWebsite("Website");
        users.setCatchPhrase("Catch Phrase");
        users.setUsername("janedoe");
        users.setSuite("Suite");
        users.setCity("Oxford");
        users.setId(123L);
        users.setPhone("4105551212");
        users.setBs("Bs");
        users.setZipcode("21654");
        users.setLng("Lng");
        users.setStreet("Street");

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users);
        albums.setTitle("Dr");

        Photos photos = new Photos();
        photos.setId(123L);
        photos.setUrl("https://example.org/example");
        photos.setTitle("Dr");
        photos.setAlbum(albums);
        photos.setThumbnailUrl("https://example.org/example");

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setLat("Lat");
        users1.setName("Name");
        users1.setCompanyName("Company Name");
        users1.setWebsite("Website");
        users1.setCatchPhrase("Catch Phrase");
        users1.setUsername("janedoe");
        users1.setSuite("Suite");
        users1.setCity("Oxford");
        users1.setId(123L);
        users1.setPhone("4105551212");
        users1.setBs("Bs");
        users1.setZipcode("21654");
        users1.setLng("Lng");
        users1.setStreet("Street");

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users1);
        albums1.setTitle("Dr");

        Photos photos1 = new Photos();
        photos1.setId(123L);
        photos1.setUrl("https://example.org/example");
        photos1.setTitle("Dr");
        photos1.setAlbum(albums1);
        photos1.setThumbnailUrl("https://example.org/example");

        ArrayList<Photos> photosList = new ArrayList<Photos>();
        photosList.add(photos1);
        photosList.add(photos);
        when(this.photoRepository.findByAlbum((Albums) any())).thenReturn(photosList);

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setLat("Lat");
        users2.setName("Name");
        users2.setCompanyName("Company Name");
        users2.setWebsite("Website");
        users2.setCatchPhrase("Catch Phrase");
        users2.setUsername("janedoe");
        users2.setSuite("Suite");
        users2.setCity("Oxford");
        users2.setId(123L);
        users2.setPhone("4105551212");
        users2.setBs("Bs");
        users2.setZipcode("21654");
        users2.setLng("Lng");
        users2.setStreet("Street");

        Albums albums2 = new Albums();
        albums2.setId(123L);
        albums2.setUser(users2);
        albums2.setTitle("Dr");

        ArrayList<Albums> albumsList = new ArrayList<Albums>();
        albumsList.add(albums2);
        when(this.albumRepository.findByUserId((Long) any())).thenReturn(albumsList);
        assertEquals(2, this.photosServiceImpl.findMyPictures(123L).size());
        verify(this.albumRepository).findByUserId((Long) any());
        verify(this.photoRepository).findByAlbum((Albums) any());
    }

    @Test
    public void testFindMyPictures5() {
        when(this.photoRepository.findByAlbum((Albums) any())).thenReturn(new ArrayList<Photos>());

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setLat("Lat");
        users.setName("Name");
        users.setCompanyName("Company Name");
        users.setWebsite("Website");
        users.setCatchPhrase("Catch Phrase");
        users.setUsername("janedoe");
        users.setSuite("Suite");
        users.setCity("Oxford");
        users.setId(123L);
        users.setPhone("4105551212");
        users.setBs("Bs");
        users.setZipcode("21654");
        users.setLng("Lng");
        users.setStreet("Street");

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users);
        albums.setTitle("Dr");

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setLat("Lat");
        users1.setName("Name");
        users1.setCompanyName("Company Name");
        users1.setWebsite("Website");
        users1.setCatchPhrase("Catch Phrase");
        users1.setUsername("janedoe");
        users1.setSuite("Suite");
        users1.setCity("Oxford");
        users1.setId(123L);
        users1.setPhone("4105551212");
        users1.setBs("Bs");
        users1.setZipcode("21654");
        users1.setLng("Lng");
        users1.setStreet("Street");

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users1);
        albums1.setTitle("Dr");

        ArrayList<Albums> albumsList = new ArrayList<Albums>();
        albumsList.add(albums1);
        albumsList.add(albums);
        when(this.albumRepository.findByUserId((Long) any())).thenReturn(albumsList);
        assertTrue(this.photosServiceImpl.findMyPictures(123L).isEmpty());
        verify(this.albumRepository).findByUserId((Long) any());
        verify(this.photoRepository, times(2)).findByAlbum((Albums) any());
    }
}

