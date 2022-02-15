package com.sparta.miniproject.Post;


import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponse;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponse;
import com.sparta.miniproject.model.Response;
import com.sparta.miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController
{
    private final PostService postService;

    // 글 작성
    @PostMapping("/post")
    public Response createMemo(@RequestBody PostPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return postService.createPost(requestDto, userDetails);
    }

    // 전체 글 조회
    @GetMapping("/post")
    public PostPostResponse getPostList(@RequestParam String postType)
    {
        return postService.getPostList(postType);
    }

    // 글 수정
    @PutMapping("/post/{postId}")
    public Response updatePost(@RequestBody PostPutRequestDto requestDto, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return postService.updatePost(requestDto,postId, userDetails);
    }

    // 글 삭제
    @DeleteMapping("post/{postId}")
    public Response deletePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return postService.deletePost(postId, userDetails);
    }

    // 해당 글 조회
    @GetMapping("/post/{postId}")
    public PostGetResponse getPost(@PathVariable Long postId)
    {
        return postService.getPost(postId);
    }




}
