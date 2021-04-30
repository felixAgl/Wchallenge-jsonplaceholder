package com.wchallange.jsonplaceholder.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photos")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    private Albums album;

    private String title;
    private String url;
    private String thumbnailUrl;
}
