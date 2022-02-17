package com.sparta.miniproject.Post;

import com.sparta.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByOrderByLikeCntDesc();
    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findAllByUser(User user);
}
