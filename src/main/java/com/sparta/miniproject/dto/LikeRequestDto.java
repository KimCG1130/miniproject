package com.sparta.miniproject.dto;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class LikeRequestDto {
    public Likes toEntity(Post post, User user) {
        return Likes.builder()
                .user(user)
                .post(post)
                .build();
    }
}
