package com.wchallange.jsonplaceholder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.UserHasAlbumRepository;
import com.wchallange.jsonplaceholder.repository.UserRepository;
import com.wchallange.jsonplaceholder.service.dto.AddressDTO;
import com.wchallange.jsonplaceholder.service.dto.CompanyDTO;
import com.wchallange.jsonplaceholder.service.dto.GeoDTO;
import com.wchallange.jsonplaceholder.service.dto.UserDTO;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {UserServiceImpl.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserHasAlbumRepository userHasAlbumRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testConsumeUser() throws RestClientException {
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
        when(this.userRepository.save((Users) any())).thenReturn(users1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setGeo(new GeoDTO());
        UserDTO userDTO = mock(UserDTO.class);
        when(userDTO.getCompany()).thenReturn(new CompanyDTO());
        when(userDTO.getAddress()).thenReturn(addressDTO);
        when(userDTO.getWebsite()).thenReturn("foo");
        when(userDTO.getPhone()).thenReturn("foo");
        when(userDTO.getEmail()).thenReturn("foo");
        when(userDTO.getUsername()).thenReturn("foo");
        when(userDTO.getName()).thenReturn("foo");
        when(userDTO.getId()).thenReturn(1L);
        when(this.restTemplate.getForObject(anyString(), (Class<Object>) any(), (Object[]) any())).thenReturn(userDTO);
        UserDTO actualConsumeUserResult = this.userServiceImpl.consumeUser(123L);
        assertEquals(
                "UserDTO(id=123, name=Name, username=janedoe, email=jane.doe@example.org, address=AddressDTO(street=Street,"
                        + " suite=Suite, city=Oxford, zipcode=21654, geo=GeoDTO(lat=Lat, lng=Lng)), phone=4105551212, website=Website,"
                        + " company=CompanyDTO(name=Company Name, catchPhrase=Catch Phrase, bs=Bs))",
                actualConsumeUserResult.toString());
        assertEquals(123L, actualConsumeUserResult.getId().longValue());
        assertEquals("Website", actualConsumeUserResult.getWebsite());
        assertEquals("Name", actualConsumeUserResult.getName());
        assertEquals("jane.doe@example.org", actualConsumeUserResult.getEmail());
        assertEquals("4105551212", actualConsumeUserResult.getPhone());
        assertEquals("janedoe", actualConsumeUserResult.getUsername());
        AddressDTO address = actualConsumeUserResult.getAddress();
        assertEquals("Street", address.getStreet());
        assertEquals("Oxford", address.getCity());
        CompanyDTO company = actualConsumeUserResult.getCompany();
        assertEquals("Company Name", company.getName());
        assertEquals("21654", address.getZipcode());
        assertEquals("AddressDTO(street=Street, suite=Suite, city=Oxford, zipcode=21654, geo=GeoDTO(lat=Lat, lng=Lng))",
                address.toString());
        assertEquals("Bs", company.getBs());
        assertEquals("Catch Phrase", company.getCatchPhrase());
        assertEquals("Suite", address.getSuite());
        GeoDTO geo = address.getGeo();
        assertEquals("Lng", geo.getLng());
        assertEquals("Lat", geo.getLat());
        verify(this.restTemplate).getForObject(anyString(), (Class<Object>) any(), (Object[]) any());
        verify(userDTO).getName();
        verify(userDTO, times(3)).getCompany();
        verify(userDTO).getWebsite();
        verify(userDTO).getPhone();
        verify(userDTO).getUsername();
        verify(userDTO).getId();
        verify(userDTO, times(6)).getAddress();
        verify(userDTO).getEmail();
        verify(this.userRepository).save((Users) any());
        verify(this.userRepository).findById((Long) any());
    }
}

