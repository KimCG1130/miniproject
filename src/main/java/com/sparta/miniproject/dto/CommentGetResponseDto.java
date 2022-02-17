package com.sparta.miniproject.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentGetResponseDto {

    private List<CommentResponseDto> commentResponseDtoListList;

}
