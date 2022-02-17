package com.sparta.miniproject.repository;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPost(Post post);
    Optional<Likes> findByUser(User user);
    Optional<Likes> findByUserAndPost(User user, Post post);

    List<Likes> findAllByUser(User user);
}
