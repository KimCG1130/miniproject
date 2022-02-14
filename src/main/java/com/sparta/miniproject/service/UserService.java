package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.UserRequestDto;
import com.sparta.miniproject.dto.UserResponseDto;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.repository.UserRepository;
import com.sparta.miniproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public User registerUser(UserRequestDto userRequestDto) {

//        SignupValidator.signupValidator(userRequestDto);

        String password = passwordEncoder.encode(userRequestDto.getPassword());
        User user = userRepository.save(userRequestDto.toEntity(password));

        System.out.println(user.toResponseDto().getUsername());
        System.out.println(user.toResponseDto().getNickname());
        System.out.println(user.toResponseDto().getId());

        return user;
    }

    public UserResponseDto getUser(UserDetailsImpl userDetails) {
        return UserResponseDto.builder()
                .username(userDetails.getUsername())
                .nickname(userDetails.getUser().getNickname())
                .build();
    }
}
