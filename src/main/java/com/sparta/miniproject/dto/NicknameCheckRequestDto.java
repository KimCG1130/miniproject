package com.sparta.miniproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NicknameCheckRequestDto {
    private String nickname;
}
