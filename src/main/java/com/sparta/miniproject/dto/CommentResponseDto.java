package com.sparta.miniproject.dto;

import com.sparta.miniproject.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CommentResponseDto {
    //Long id;
    Long commentId;
    String comment;
    String nickname;
    LocalDateTime commentDate;

}
