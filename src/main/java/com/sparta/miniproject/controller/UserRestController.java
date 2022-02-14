package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.UserResponseDto;
import com.sparta.miniproject.security.UserDetailsImpl;
import com.sparta.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user/islogin")
//    public UserResponseDto getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return userService.getUser(userDetails);
//    }
}
