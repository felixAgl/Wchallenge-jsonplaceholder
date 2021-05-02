package com.wchallange.jsonplaceholder.service.mapper;


import com.wchallange.jsonplaceholder.domain.Users;
import com.wchallange.jsonplaceholder.service.dto.AddressDTO;
import com.wchallange.jsonplaceholder.service.dto.CompanyDTO;
import com.wchallange.jsonplaceholder.service.dto.GeoDTO;
import com.wchallange.jsonplaceholder.service.dto.UserDTO;

public class UserMapper {

    public static UserDTO toUserDto(Users user) {
        UserDTO dto = new UserDTO();
        AddressDTO address = new AddressDTO();
        CompanyDTO company = new CompanyDTO();
        GeoDTO geo = new GeoDTO();
        geo.setLat(user.getLat());
        geo.setLng(user.getLng());
        address.setCity(user.getCity());
        address.setStreet(user.getStreet());
        address.setSuite(user.getSuite());
        address.setZipcode(user.getZipcode());
        address.setGeo(geo);
        company.setBs(user.getBs());
        company.setCatchPhrase(user.getCatchPhrase());
        company.setName(user.getCompanyName());
        dto.setAddress(address);
        dto.setName(user.getName());
        dto.setCompany(company);
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setWebsite(user.getWebsite());
        return dto;
    }


    public static Users toUserEntity(UserDTO dto) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setWebsite(dto.getWebsite());
        user.setStreet(dto.getAddress().getStreet());
        user.setSuite(dto.getAddress().getSuite());
        user.setCity(dto.getAddress().getCity());
        user.setZipcode(dto.getAddress().getZipcode());
        user.setLat(dto.getAddress().getGeo().getLat());
        user.setLng(dto.getAddress().getGeo().getLng());
        user.setCompanyName(dto.getCompany().getName());
        user.setCatchPhrase(dto.getCompany().getCatchPhrase());
        user.setBs(dto.getCompany().getBs());
        return user;
    }
}
