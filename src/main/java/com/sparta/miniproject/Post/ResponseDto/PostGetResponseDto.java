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
    Long postId;

    LocalDateTime postDate;

    String title;

    String imgUrl;

    String country;

    String city;

    String evalution;

    String content;

    int likeCnt;

    int commentCnt;

    String nickname;

    public PostGetResponseDto(Post post)
    {
       this.postId = post.getPostId();
       this.postDate = post.getCreatedAt();
       this.title =post.getTitle();
       this.imgUrl =post.getImgUrl();
       this.country =post.getCountry();
       this.city =post.getCity();
       this.evalution = post.getEvalution();
       this.content = post.getContent();
       this.likeCnt =post.getLikeCnt();
       this.commentCnt = post.getCommentCnt();
       this.nickname =post.getId().getNickname();

    }
}
