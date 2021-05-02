package com.wchallange.jsonplaceholder.domain;


import com.wchallange.jsonplaceholder.domain.enumeration.AlbumPermissions;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_has_albums")
public class UserHasAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Albums album;


    @Enumerated(EnumType.STRING)
    private AlbumPermissions albumPermissions;
}
