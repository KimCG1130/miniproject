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
    private Long commentId;
    private String comment;
    private String nickname;
    private LocalDateTime commentDate;

    public CommentGetResponseDto toCommentGetResponseDto(List<CommentResponseDto> commentResponseDtos) {
        return CommentGetResponseDto.builder()
                .commentResponseDtoListList(commentResponseDtos)
                .build();
    }

}
