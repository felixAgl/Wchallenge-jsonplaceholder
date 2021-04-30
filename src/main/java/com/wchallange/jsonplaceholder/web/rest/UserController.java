package com.wchallange.jsonplaceholder.web.rest;

import com.wchallange.jsonplaceholder.service.dto.UserDTO;
import com.wchallange.jsonplaceholder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> consumeUser(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.consumeUser(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> consumeAllUsers() {
        return new ResponseEntity<>(userService.consumeAllUsers(), HttpStatus.OK);
    }

}
