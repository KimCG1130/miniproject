package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.LoginDto;
import com.sparta.miniproject.dto.UserRequestDto;
import com.sparta.miniproject.model.ReturnUser;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.security.UserDetailsImpl;
import com.sparta.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*",allowCredentials = "false")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto requestDto) {
        User user = userService.registerUser(requestDto);
        return ResponseEntity.ok(user);
    }

    // 로그인
    @PostMapping("/user/login")
    public ReturnUser login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @GetMapping("/user/loginInfo")
    public ResponseEntity<User> login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.loginInfo(userDetails));
    }
}


