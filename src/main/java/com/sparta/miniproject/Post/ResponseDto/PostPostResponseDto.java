package com.sparta.miniproject.Post.ResponseDto;

import com.sparta.miniproject.Post.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class PostPostResponseDto
{
    Long postId;

    String title;

    String imgUrl;

    String country;

    String city;

    int likeCnt;

    int commentCnt;

    String nickname;

    public PostPostResponseDto(Post post)
    {
       this.postId = post.getPostId();
       this.title =post.getTitle();
       this.imgUrl =post.getImgUrl();
       this.country =post.getCountry();
       this.city =post.getCity();
       this.likeCnt =post.getLikeCnt();
       this.commentCnt = post.getCommentCnt();
       this.nickname =post.getId().getNickname();
    }
}
