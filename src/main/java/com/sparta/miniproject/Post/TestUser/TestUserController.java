package com.sparta.miniproject.Post.TestUser;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestUserController
{
    private final TestUserService testUserService;

    @PostMapping("/test/user")
    public TestUser createMemo(@RequestBody TestUserRequestDto requestDto)
    {

        return testUserService.createUser(requestDto);
    }
}
