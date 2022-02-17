package com.sparta.miniproject.repository;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByCreatedAtDesc();
    List<Comment> findAllByPost(Post index);
}
