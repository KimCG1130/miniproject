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
    private Long postId;

    private String title;

    private String imgUrl;

    private String country;

    private String city;

    private int likeCnt;

    private int commentCnt;

    private String nickname;

    public PostPostResponseDto(Post post, int likeCnt, int commentCnt)
    {
       this.postId = post.getPostId();
       this.title =post.getTitle();
       this.imgUrl =post.getImgUrl();
       this.country =post.getCountry();
       this.city =post.getCity();
       this.likeCnt = likeCnt;
       this.commentCnt = commentCnt;
       this.nickname =post.getUser().getNickname();
    }

    public PostPostResponseDto(Post post)
    {
        this.postId = post.getPostId();
        this.title =post.getTitle();
        this.imgUrl =post.getImgUrl();
        this.country =post.getCountry();
        this.city =post.getCity();
        this.likeCnt = post.getLikeCnt();
        this.commentCnt = post.getCommentCnt();
        this.nickname =post.getUser().getNickname();
    }
}
