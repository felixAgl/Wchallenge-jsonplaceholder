package com.wchallange.jsonplaceholder.controller;

import com.wchallange.jsonplaceholder.dto.UserDTO;
import com.wchallange.jsonplaceholder.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public Object consumeUser(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.consumeUser(id);
            return userDTO;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/users")
    public UserDTO[] consumeAllUser() {
        return userService.consumeAllUser();

    }

}
