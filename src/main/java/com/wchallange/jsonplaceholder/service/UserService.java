package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.dto.UserDTO;

public interface UserService {

    UserDTO consumeUser(Long id);

    UserDTO[] consumeAllUser();
}
