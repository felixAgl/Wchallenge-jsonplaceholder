package com.wchallange.jsonplaceholder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.UserRepository;
import com.wchallange.jsonplaceholder.service.dto.AlbumDTO;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {AlbumServiceImpl.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
public class AlbumServiceImplTest {
    @MockBean
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumServiceImpl albumServiceImpl;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testConsumeAlbum() throws RestClientException {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.restTemplate.getForObject(anyString(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new AlbumDTO());

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

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users1);
        albums.setTitle("Dr");
        Optional<Albums> ofResult1 = Optional.<Albums>of(albums);

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

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users2);
        albums1.setTitle("Dr");
        when(this.albumRepository.save((Albums) any())).thenReturn(albums1);
        when(this.albumRepository.findById((Long) any())).thenReturn(ofResult1);
        AlbumDTO actualConsumeAlbumResult = this.albumServiceImpl.consumeAlbum(123L);
        assertEquals(123L, actualConsumeAlbumResult.getId().longValue());
        assertEquals(123L, actualConsumeAlbumResult.getUserId().longValue());
        assertEquals("Dr", actualConsumeAlbumResult.getTitle());
        verify(this.albumRepository).save((Albums) any());
        verify(this.albumRepository).findById((Long) any());
        verify(this.restTemplate).getForObject(anyString(), (Class<Object>) any(), (Object[]) any());
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    public void testConsumeAlbum2() throws RestClientException {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<Users>empty());
        when(this.restTemplate.getForObject(anyString(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new AlbumDTO());

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
        Optional<Albums> ofResult = Optional.<Albums>of(albums);
        when(this.albumRepository.findById((Long) any())).thenReturn(ofResult);
        this.albumServiceImpl.consumeAlbum(123L);
        verify(this.restTemplate).getForObject(anyString(), (Class<Object>) any(), (Object[]) any());
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    public void testConsumeAlbum3() throws RestClientException {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        AlbumDTO albumDTO = mock(AlbumDTO.class);
        when(albumDTO.getTitle()).thenReturn("foo");
        when(albumDTO.getId()).thenReturn(1L);
        when(albumDTO.getUserId()).thenReturn(1L);
        when(this.restTemplate.getForObject(anyString(), (Class<Object>) any(), (Object[]) any())).thenReturn(albumDTO);

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

        Albums albums = new Albums();
        albums.setId(123L);
        albums.setUser(users1);
        albums.setTitle("Dr");
        Optional<Albums> ofResult1 = Optional.<Albums>of(albums);

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

        Albums albums1 = new Albums();
        albums1.setId(123L);
        albums1.setUser(users2);
        albums1.setTitle("Dr");
        when(this.albumRepository.save((Albums) any())).thenReturn(albums1);
        when(this.albumRepository.findById((Long) any())).thenReturn(ofResult1);
        AlbumDTO actualConsumeAlbumResult = this.albumServiceImpl.consumeAlbum(123L);
        assertEquals(123L, actualConsumeAlbumResult.getId().longValue());
        assertEquals(123L, actualConsumeAlbumResult.getUserId().longValue());
        assertEquals("Dr", actualConsumeAlbumResult.getTitle());
        verify(albumDTO).getTitle();
        verify(albumDTO).getId();
        verify(albumDTO).getUserId();
        verify(this.albumRepository).save((Albums) any());
        verify(this.albumRepository).findById((Long) any());
        verify(this.restTemplate).getForObject(anyString(), (Class<Object>) any(), (Object[]) any());
        verify(this.userRepository).findById((Long) any());
    }
}

