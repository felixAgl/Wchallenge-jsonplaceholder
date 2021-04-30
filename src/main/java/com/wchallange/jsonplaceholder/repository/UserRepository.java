package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.UserHasAlbum;
import com.wchallange.jsonplaceholder.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

}
