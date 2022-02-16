package com.sparta.miniproject.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    //Long id;
    Long commentId;
    String comment;
    String nickname;
    LocalDateTime commentDate;

    public CommentGetResponseDto toCommentGetResponseDto(List<CommentResponseDto> commentResponseDtos) {
        return CommentGetResponseDto.builder()
                .commentResponseDtoListList(commentResponseDtos)
                .build();
    }

}
