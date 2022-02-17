package com.sparta.miniproject.Post;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.model.Comment;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.Timestamped;
import com.sparta.miniproject.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(columnDefinition = "mediumblob")
    private String imgUrl;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String evaluation;

    @Column(nullable = false)
    private int likeCnt;

    @Column(nullable = false)
    private int commentCnt;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"post"})
    private List<Likes> likes = new ArrayList<Likes>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"post"})
    private List<Comment> commentList = new ArrayList<Comment>();

    public Post(PostPostRequestDto requestDto, User user)
    {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.country =requestDto.getCountry();
        this.city = requestDto.getCity();
        this.evaluation = requestDto.getEvaluation();
        this.likeCnt = 0;
        this.commentCnt = 0;
        this.user = user;
    }

    public void update(PostPutRequestDto requestDto)
    {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.country =requestDto.getCountry();
        this.city = requestDto.getCity();
        this.evaluation = requestDto.getEvaluation();
        this.likeCnt = 0;
        this.commentCnt = 0;

    }



}