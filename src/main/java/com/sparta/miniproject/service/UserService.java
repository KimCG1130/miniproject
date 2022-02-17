package com.sparta.miniproject.service;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.Post.PostRepository;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponse;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponseDto;
import com.sparta.miniproject.dto.IdCheckRequestDto;
import com.sparta.miniproject.dto.LoginDto;
import com.sparta.miniproject.dto.NicknameCheckRequestDto;
import com.sparta.miniproject.dto.UserRequestDto;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.ReturnUser;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.model.UserRoleEnum;
import com.sparta.miniproject.repository.LikeRepository;
import com.sparta.miniproject.repository.UserRepository;
import com.sparta.miniproject.security.JwtTokenProvider;
import com.sparta.miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public User registerUser(UserRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        Optional<User> userNick = userRepository.findByNickname(requestDto.getNickname());

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        if (userNick.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }

        if (!username.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
            throw new IllegalArgumentException("아이디는 email 형식으로 작성가능합니다.");
        }

        if (requestDto.getUsername() == null) {
            throw new NullPointerException("아이디를 입력해주세요");
        }
        if (Objects.equals(requestDto.getUsername(), "")) {
            throw new NullPointerException("아이디를 입력해주세요!!!!!!!!!");
        }
        if (requestDto.getNickname() == null) {
            throw new NullPointerException("닉네임을 입력해주세요");
        }
        if (requestDto.getPassword() == null) {
            throw new NullPointerException("비밀번호를 입력해주세요");
        }
        if (requestDto.getPasswordcheck() == null) {
            throw new NullPointerException("비밀번호확인을 입력해주세요");
        }
        if (Objects.equals(requestDto.getPassword(), "")) {
            throw new NullPointerException("비밀번호를 입력해주세요!!!!!!!!!!!!");
        }
        if (Objects.equals(requestDto.getNickname(), "")) {
            throw new NullPointerException("닉네임을 입력해주세요!!!!!!!!!!!!!!!");
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordcheck())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인 값이 다릅니다.");
        }


        String nickname = requestDto.getNickname();

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String user_profile = requestDto.getUser_profile();

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, nickname, password, user_profile, role);


        return userRepository.save(user);
    }


    //로그인 서비스
    //로그인 dto에 username과 password를 가지고 존재하는지 확인을 해줍니다 userrepository를 이용하여 db에서 체크
    //존재하지 않거나 비밀번호가 맞지 않을시 오류를 내주고 그렇지 않을경우 토큰을 발행합니다.
    public ReturnUser login(LoginDto loginDto) {
        ReturnUser returnUser = new ReturnUser();
        try {

            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            returnUser.setToken(jwtTokenProvider.createToken(member.getUsername()));
            returnUser.setUsername(member.getUsername());
            returnUser.setNickname(member.getNickname());
            return returnUser;
        } catch (IllegalArgumentException e) {
            returnUser.setMsg(e.getMessage());
            return returnUser;
        }
    }

    @Transactional
    public User loginInfo(UserDetailsImpl userDetails) {
        return userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("로그인 상태가 아닌 사용자입니다")
        );
    }

    public Boolean idDuplicate(IdCheckRequestDto idCheckRequestDto) {
        if (userRepository.findByUsername(idCheckRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
        return true;
    }

    public Boolean nicknameDuplicate(NicknameCheckRequestDto nicknameCheckRequestDto) {
        if (userRepository.findByNickname(nicknameCheckRequestDto.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        return true;
    }

    public PostPostResponse myPost(UserDetailsImpl userDetails) {
        List<Post> postList = postRepository.findAllByUser(userDetails.getUser());

        List<PostPostResponseDto> postPostResponseDtoList = new ArrayList<>();

        for (Post post: postList) {
            PostPostResponseDto postPostResponseDto = new PostPostResponseDto(post);
            postPostResponseDtoList.add(postPostResponseDto);
        }

        PostPostResponse postResponse = new PostPostResponse();

        postResponse.setStatus(200);
        postResponse.setPostResponseDto(postPostResponseDtoList);

        return postResponse;
    }

    public PostPostResponse myLike(UserDetailsImpl userDetails) {
        List<Likes> likes = likeRepository.findAllByUser(userDetails.getUser());
        List<PostPostResponseDto> postPostResponseDtoList = new ArrayList<>();

        for (Likes like : likes) {
            PostPostResponseDto postPostResponseDto = new PostPostResponseDto(like.getPost());
            postPostResponseDtoList.add(postPostResponseDto);
        }

        PostPostResponse postResponse = new PostPostResponse();

        postResponse.setStatus(200);
        postResponse.setPostResponseDto(postPostResponseDtoList);

        return postResponse;
    }
}