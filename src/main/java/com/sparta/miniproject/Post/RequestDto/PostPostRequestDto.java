package com.sparta.miniproject.Post.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostPostRequestDto
{
    private String nickname;

    private String imgUrl;

    private String title;

    private String country;

    private String city;

    private String evaluation;

    private String content;
}
