package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.Comments;
import com.wchallange.jsonplaceholder.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByPost(Posts post);
}
