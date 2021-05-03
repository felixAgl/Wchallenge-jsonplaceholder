package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByUserId(Long userId);
}
