package com.sparta.miniproject.Post.TestUser;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<TestUser, Long>
{
    TestUser findTestUserByNickname(String nickname);
}
