package com.sparta.miniproject.Post.RequestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class PostPutRequestDto
{
    String imgUrl;

    String title;

    String country;

    String city;

    String evaluation;

    String content;
}
