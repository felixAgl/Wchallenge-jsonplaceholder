package com.wchallange.jsonplaceholder.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private AddressDTO address;
    private String phone;
    private String website;
    private CompanyDTO company;

}
