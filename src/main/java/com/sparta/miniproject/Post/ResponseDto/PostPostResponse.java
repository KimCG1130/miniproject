package com.sparta.miniproject.Post.ResponseDto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostPostResponse
{
    private int status;

    private List<PostPostResponseDto> postResponseDto;


}
