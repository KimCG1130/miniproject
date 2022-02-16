package com.sparta.miniproject.model;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.dto.LikeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

    public LikeResponseDto toDto(boolean bool, int likeCnt) {
        return LikeResponseDto.builder()
                .nickname(user.getNickname())
                .islike(bool)
                .likeCnt(likeCnt)
                .build();
    }
}
