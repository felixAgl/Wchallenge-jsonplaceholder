package com.wchallange.jsonplaceholder.service.impl;

import com.wchallange.jsonplaceholder.domain.Albums;
import com.wchallange.jsonplaceholder.domain.Photos;
import com.wchallange.jsonplaceholder.domain.UserHasAlbum;
import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.domain.enumeration.AlbumPermissions;
import com.wchallange.jsonplaceholder.repository.AlbumRepository;
import com.wchallange.jsonplaceholder.repository.PhotoRepository;
import com.wchallange.jsonplaceholder.repository.UserHasAlbumRepository;
import com.wchallange.jsonplaceholder.repository.UserRepository;
import com.wchallange.jsonplaceholder.service.dto.UserDTO;
import com.wchallange.jsonplaceholder.domain.enumeration.Url;
import com.wchallange.jsonplaceholder.service.UserService;
import com.wchallange.jsonplaceholder.service.dto.UserHasAlbumDTO;
import com.wchallange.jsonplaceholder.service.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserHasAlbumRepository userHasAlbumRepository;

    private final String USER_API = Url.API.getUrl().concat(Url.USERS.getUrl());
    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO consumeUser(Long id) {
        String url = USER_API + "/{id}";
        UserDTO dto =  restTemplate.getForObject(url, UserDTO.class, id);
        Users user =  UserMapper.toUserEntity(dto);
        Optional<Users> userFound = userRepository.findById(user.getId());
        UserDTO toBeReturned = new UserDTO();
        if (userFound.isPresent()){
            if (!userFound.get().equals(user)){
                Users result = userRepository.save(user);
                toBeReturned = UserMapper.toUserDto(result);
            }else {
                toBeReturned = UserMapper.toUserDto(userFound.get());
            }
        }else {
            Users result = userRepository.save(user);
            toBeReturned = UserMapper.toUserDto(result);
        }
        return toBeReturned;
    }

    @Override
    public UserHasAlbum shareMyAlbum(Long userId, Long album, Long userToShare, AlbumPermissions albumPermissions) throws Exception {
        Optional<Albums> albumFound = albumRepository.findById(album);
        if (albumFound.isPresent()){
            Optional<Albums> albumFoundById = albumRepository.findByIdAndUserId(album, userId);
            if (albumFoundById.isPresent()){
                Optional<UserHasAlbum> userHasAlbum = userHasAlbumRepository.findByUserIdAndAlbumId(userToShare, album);
                Optional<Users> userToShareFound = userRepository.findById(userToShare);
                if (userToShareFound.isPresent()){
                    if (!userHasAlbum.isPresent()){
                        UserHasAlbum hasAlbumToSave = new UserHasAlbum();
                        hasAlbumToSave.setAlbumPermissions(albumPermissions);
                        hasAlbumToSave.setAlbum(albumFound.get());
                        hasAlbumToSave.setUser(userToShareFound.get());
                        return userHasAlbumRepository.save(hasAlbumToSave);
                    }else {
                        userHasAlbum.get().setAlbumPermissions(albumPermissions);
                        return userHasAlbumRepository.save(userHasAlbum.get());
                    }
                }else {
                    throw new Exception("El usuario al que quieres compartir no existe");
                }

            }else {
                throw new Exception("Este album no pertenece al usuario");
            }
        }else {
            throw new Exception("No album found");
        }
    }


    @Override
    public List<UserDTO> consumeAllUsers() {
        UserDTO[] userDtoList = restTemplate.getForObject(USER_API, UserDTO[].class);
        List<UserDTO> userList = Arrays.asList(userDtoList);
        List<Users> users = userList.stream().map(UserMapper::toUserEntity).collect(Collectors.toList());
        List<UserDTO> userToBeReturned = new ArrayList<>();
        users.forEach(user -> {
            Optional<Users> userFound = userRepository.findById(user.getId());
            if (userFound.isPresent()){
                if (!userFound.get().equals(user)){
                    Users result = userRepository.save(user);
                    userToBeReturned.add(UserMapper.toUserDto(result));
                }else{
                    userToBeReturned.add(UserMapper.toUserDto(user));
                }
            }else {
                Users result = userRepository.save(user);
                userToBeReturned.add(UserMapper.toUserDto(result));
            }
        });
        return userToBeReturned;
    }


}
