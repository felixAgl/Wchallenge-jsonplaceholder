package com.wchallange.jsonplaceholder.repository;

import com.wchallange.jsonplaceholder.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
}
