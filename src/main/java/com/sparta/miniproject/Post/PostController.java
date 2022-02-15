package com.sparta.miniproject.Post;


import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponse;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponse;
import com.sparta.miniproject.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController
{
    private final PostService postService;

    // 글 작성
    @PostMapping("/post")
    public Response createMemo(@RequestBody PostPostRequestDto requestDto)
    {
        return postService.createPost(requestDto);
    }

    // 전체 글 조회
    @GetMapping("/post")
    public PostPostResponse getPostList(@RequestParam String postType)
    {
        return postService.getPostList(postType);
    }

    // 글 수정
    @PutMapping("/post/{postId}")
    public Response updatePost(@RequestBody PostPutRequestDto requestDto, @PathVariable Long postId)
    {
        return postService.updatePost(requestDto,postId);
    }

    // 글 삭제
    @DeleteMapping("post/{postId}")
    public Response deletePost(@PathVariable Long postId)
    {
        return postService.deletePost(postId);
    }

    // 해당 글 조회
    @GetMapping("/post/{postId}")
    public PostGetResponse getPost(@PathVariable Long postId)
    {
        return postService.getPost(postId);
    }




}
