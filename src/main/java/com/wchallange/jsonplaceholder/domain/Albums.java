package com.wchallange.jsonplaceholder.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "albums")
public class Albums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;
    private String title;
}
