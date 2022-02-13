package com.sparta.miniproject.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByOrderByLikeCnt();
    List<Post> findAllByOrderByCreatedAtDesc();
}
