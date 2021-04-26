package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.dto.UserDTO;
import com.wchallange.jsonplaceholder.enumeration.Url;
import com.wchallange.jsonplaceholder.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final String USER_API = Url.URL.getUrl().concat(Url.USERS.getUrl());
    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO consumeUser(Long id) {
        String url = USER_API + "/{id}";
        return restTemplate.getForObject(url, UserDTO.class, id);
    }

    @Override
    public UserDTO[] consumeAllUser() {
        return restTemplate.getForObject(USER_API, UserDTO[].class);
    }
}
