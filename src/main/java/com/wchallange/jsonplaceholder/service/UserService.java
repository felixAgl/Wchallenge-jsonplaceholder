package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO consumeUser(Long id);

    List<UserDTO> consumeAllUsers();
}
