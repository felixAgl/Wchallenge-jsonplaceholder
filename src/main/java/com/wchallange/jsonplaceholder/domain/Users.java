package com.wchallange.jsonplaceholder.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private String name;

    private String username;
    private String email;

    private String phone;
    private String website;

    // Address
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    // geo
    private String lat;
    private String lng;

    // Company
    private String companyName;
    private String catchPhrase;
    private String bs;


}
