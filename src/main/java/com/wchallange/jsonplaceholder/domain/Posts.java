package com.wchallange.jsonplaceholder.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    private String title;

    @Column(length = 500)
    private String body;

}
