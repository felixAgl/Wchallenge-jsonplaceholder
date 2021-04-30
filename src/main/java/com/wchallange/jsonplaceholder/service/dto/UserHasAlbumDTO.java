package com.wchallange.jsonplaceholder.service.dto;


import com.wchallange.jsonplaceholder.domain.enumeration.AlbumPermissions;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserHasAlbumDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long albumId;

    private String albumTitle;

    private AlbumPermissions albumPermissions;
}
