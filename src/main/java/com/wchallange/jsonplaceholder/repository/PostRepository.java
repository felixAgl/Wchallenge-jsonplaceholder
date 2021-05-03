package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
}
