package com.sparta.miniproject.Post.TestUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestUserService
{
    private final TestUserRepository testUserRepository;

    public TestUser createUser(TestUserRequestDto requestDto)
    {
        TestUser user = new TestUser(requestDto);
        return testUserRepository.save(user);
    }
}
