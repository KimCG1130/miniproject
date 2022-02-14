package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.UserRequestDto;
import com.sparta.miniproject.dto.UserResponseDto;
import com.sparta.miniproject.security.UserDetailsImpl;
import com.sparta.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*",allowCredentials = "false")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login() {
        System.out.println("getlogin");
        return "login";
    }

    @PostMapping("/user/signup")
    public String registerUser(UserRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

//    @GetMapping("/user/islogin")
//    public String islogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return "index.html";
//    }
}

