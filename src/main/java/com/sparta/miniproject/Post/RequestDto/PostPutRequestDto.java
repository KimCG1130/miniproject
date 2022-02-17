package com.sparta.miniproject.Post.RequestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class PostPutRequestDto
{
    private String imgUrl;

    private String title;

    private String country;

    private String city;

    private String evaluation;

    private String content;
}
