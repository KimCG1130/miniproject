package com.sparta.miniproject.Post;

import com.sparta.miniproject.Post.RequestDto.PostPostRequestDto;
import com.sparta.miniproject.Post.RequestDto.PostPutRequestDto;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponse;
import com.sparta.miniproject.Post.ResponseDto.PostGetResponseDto;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponse;
import com.sparta.miniproject.Post.ResponseDto.PostPostResponseDto;
import com.sparta.miniproject.model.Comment;
import com.sparta.miniproject.model.Likes;
import com.sparta.miniproject.model.Response;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.repository.CommentRepository;
import com.sparta.miniproject.repository.LikeRepository;
import com.sparta.miniproject.repository.UserRepository;
import com.sparta.miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Response createPost(PostPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        User user = userRepository.findByNickname(userDetails.getUser().getNickname()).orElse(null);

        Post post = new Post(requestDto, user);
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
            DBresponse = postRepository.findAllByOrderByCreatedAtAsc();
        }
        else if ( postType.equals("like"))
        {
            DBresponse = postRepository.findAllByOrderByLikeCntDesc();
        }
        else
        {
            throw new IllegalArgumentException("???????????? ?????? postType");
        }

        for ( Post index : DBresponse)
        {
            List<Comment> commentList = commentRepository.findAllByPost(index);
            List<Likes> likesList = likeRepository.findAllByPost(index);

            PostPostResponseDto postResponseDto = new PostPostResponseDto(index, likesList.size(), commentList.size());
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

    @Transactional // SQL ????????? ???????????? ?????? ??????????????? ?????????
    public Response updatePost(PostPutRequestDto requestDto, Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Post getPost= postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("?????? ????????? ??????") );

        if (!getPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("???????????? ???????????? ????????????.");
        }

        getPost.update(requestDto);

        Response response = new Response();
        response.setStatus(200);

        return response;

    }

    public Response deletePost(Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("?????? ???????????? ????????????.")
        );

        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("???????????? ???????????? ????????????.");
        }

        postRepository.deleteById(postId);

        Response response = new Response();
        response.setStatus(200);

        return response;
    }

    public PostGetResponse getPost(Long postId, UserDetailsImpl userDetails)
    {
        Post getPost =  postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("?????? ?????? ???????????? ????????????."));

        Likes likes = likeRepository.findByUserAndPost(userDetails.getUser(), getPost).orElse(null);

        List<Comment> commentList = commentRepository.findAllByPost(getPost);

        List<Likes> likesList = getPost.getLikes();
        boolean islike = false;
        islike = likes != null;

        PostGetResponseDto postGetResponseDto = new PostGetResponseDto(getPost, likesList.size(), islike, commentList.size());

        PostGetResponse postGetResponse = new PostGetResponse();

        postGetResponse.setStatus(200);
        postGetResponse.setGetResponseDto(postGetResponseDto);

        return postGetResponse;
    }
}
