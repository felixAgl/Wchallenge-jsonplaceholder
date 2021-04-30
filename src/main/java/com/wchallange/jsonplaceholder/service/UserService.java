package com.wchallange.jsonplaceholder.service;

import com.wchallange.jsonplaceholder.domain.UserHasAlbum;
import com.wchallange.jsonplaceholder.domain.enumeration.AlbumPermissions;
import com.wchallange.jsonplaceholder.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO consumeUser(Long id);

    UserHasAlbum shareMyAlbum(Long userId, Long album, Long userToShare, AlbumPermissions albumPermissions) throws Exception;

    List<UserDTO> consumeAllUsers();

}
