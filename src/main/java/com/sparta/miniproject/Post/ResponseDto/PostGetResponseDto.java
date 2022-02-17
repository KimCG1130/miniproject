package com.sparta.miniproject.Post.ResponseDto;

import com.sparta.miniproject.Post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class PostGetResponseDto
{
    private Long postId;

    private LocalDateTime postDate;

    private String title;

    private String imgUrl;

    private String country;

    private String city;

    private String evaluation;

    private String content;

    private int likeCnt;

    private int commentCnt;

    private boolean islike;

    private String nickname;

    public PostGetResponseDto(Post post, int likeCnt, boolean islike, int commentCnt)
    {
       this.postId = post.getPostId();
       this.postDate = post.getCreatedAt();
       this.title =post.getTitle();
       this.imgUrl =post.getImgUrl();
       this.country =post.getCountry();
       this.city =post.getCity();
       this.evaluation = post.getEvaluation();
       this.content = post.getContent();
       this.likeCnt = likeCnt;
       this.islike = islike;
       this.commentCnt = commentCnt;
       this.nickname =post.getUser().getNickname();

    }
}
