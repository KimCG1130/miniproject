package com.sparta.miniproject.Post;



import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.Post.TestUser.TestUser;
import com.sparta.miniproject.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post extends Timestamped
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imgUrl;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String evalution;

    @Column(nullable = false)
    private int likeCnt;

    @Column(nullable = false)
    private int commentCnt;

    @ManyToOne
    @JoinColumn ( name = "user_id")
    private TestUser Id;

    public Post(PostPostRequestDto requestDto, TestUser user)
    {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.country =requestDto.getCountry();
        this.city = requestDto.getCity();
        this.evalution = requestDto.getEvaluation();
        this.likeCnt = 0;
        this.commentCnt = 0;
        this.Id = user;
    }

    public void update(PostPutRequestDto requestDto)
    {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.country =requestDto.getCountry();
        this.city = requestDto.getCity();
        this.evalution = requestDto.getEvaluation();
        this.likeCnt = 0;
        this.commentCnt = 0;

    }



}