package com.wchallange.jsonplaceholder.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Posts post;

    private String name;
    private String email;

    @Column(length = 500)
    private String body;
}
