package com.sparta.miniproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String username;
    private String nickname;
}

