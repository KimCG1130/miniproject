package com.sparta.miniproject.service;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.Post.PostRepository;
import com.sparta.miniproject.dto.LikeRequestDto;
import com.sparta.miniproject.dto.LikeResponseDto;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.repository.LikeRepository;
import com.sparta.miniproject.repository.UserRepository;
import com.sparta.miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public LikeResponseDto clickLike(Long postId, UserDetailsImpl userDetails) {
        LikeRequestDto likeRequestDto = new LikeRequestDto();
        boolean bool = false;
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("찾으시는 글이 존재하지 않습니다.")
        );

        Likes likesCheck = likeRepository.findByUserAndPost(userDetails.getUser(), post).orElse(null);
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        System.out.println("likes 동작");

        Likes likes;
        if (likesCheck == null) {
            likes = likeRepository.save(likeRequestDto.toEntity(post, user));
            bool = true;
        }
        else {
            likeRepository.deleteById(likesCheck.getId());
            likes = likeRepository.findByUser(userDetails.getUser()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 유저")
            );
        }
        List<Likes> likesList = likeRepository.findAllByPost(post);
        System.out.println("likes 리턴 직전");
        return likes.toDto(bool, likesList.size());
    }
}
