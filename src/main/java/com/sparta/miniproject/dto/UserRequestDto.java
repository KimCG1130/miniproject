package com.sparta.miniproject.dto;

import com.sparta.miniproject.model.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String username;
    private String nickname;
    private String password;
    private String passwordCheck;

    public User toEntity(String password){
        return User.builder()
                .nickname(nickname)
                .username(username)
                .password(password)
                .build();
    }
}