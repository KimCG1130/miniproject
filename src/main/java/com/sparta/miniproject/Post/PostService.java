package com.sparta.miniproject.Post;

import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponse;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponseDto;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponse;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponseDto;
import com.sparta.miniproject.Post.TestUser.TestUser;
import com.sparta.miniproject.Post.TestUser.TestUserRepository;
import com.sparta.miniproject.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService
{
    private final PostRepository postRepository;
    private final TestUserRepository userRepository;

    public Response createPost(PostPostRequestDto requestDto)
    {
        TestUser user = userRepository.findTestUserByNickname(requestDto.getNickname());

        Post post = new Post(requestDto,user);
        postRepository.save(post);

        Response response = new Response();
        response.setStatus(200);

        return response;
    }

    public PostPostResponse getPostList(String postType)
    {
//        String sort = postType.getPostType();


        List<PostPostResponseDto> Response = new ArrayList<>();
        List<Post> DBresponse;

        if ( postType.equals("time"))
        {
            DBresponse = postRepository.findAllByOrderByCreatedAtDesc();
        }
        else if ( postType.equals("like"))
        {
            DBresponse = postRepository.findAllByOrderByLikeCnt();
        }
        else
        {
            throw new IllegalArgumentException("정해지지 않은 postType");
        }

        for ( Post index : DBresponse)
        {
            PostPostResponseDto postResponseDto = new PostPostResponseDto(index);
            Response.add(postResponseDto);
//            System.out.println(postResponseDto.getPostId());
//            System.out.println(postResponseDto.getTitle());

        }

        PostPostResponse postResponse = new PostPostResponse();

        postResponse.setStatus(200);
        postResponse.setPostResponseDto(Response);

//        System.out.println(postResponse.getStatus());
//        System.out.println(postResponse.getPostResponseDto().size());
//        System.out.println(postResponse.getPostResponseDto().get(0).getPostId());

        return postResponse;


    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Response updatePost(PostPutRequestDto requestDto, Long postId)
    {
        Post getPost= postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 포스트 없음") );

        getPost.update(requestDto);

        Response response = new Response();
        response.setStatus(200);

        return response;

    }

    public Response deletePost(Long postId)
    {
        postRepository.deleteById(postId);

        Response response = new Response();
        response.setStatus(200);

        return response;
    }

    public PostGetResponse getPost(Long postId)
    {
        Post getPost =  postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 글이 존재하지 않습니다."));

        PostGetResponseDto postGetResponseDto = new PostGetResponseDto(getPost);

        PostGetResponse postGetResponse = new PostGetResponse();

        postGetResponse.setStatus(200);
        postGetResponse.setGetResponseDto(postGetResponseDto);

        return postGetResponse;
    }
}
