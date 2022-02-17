package com.sparta.miniproject.service;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.Post.PostRepository;
import com.sparta.miniproject.dto.CommentGetResponseDto;
import com.sparta.miniproject.dto.CommentRequestDto;
import com.sparta.miniproject.dto.CommentResponseDto;
import com.sparta.miniproject.model.Comment;
import com.sparta.miniproject.model.User;
import com.sparta.miniproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

//    @Transactional
//    public void saveComment(Long id, CommentRequestDto commentRequestDto) {
////        Post post = postRepository.findById(id).orElseThrow(
////                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
////        );
//
//        Comment comment = Comment.builder()
//                //.post(post)
//                .comment(commentRequestDto.getComment())
//                //.user(user)
//                .build();
//
//        commentRepository.save(comment);
//
//    }
//예시
//    @Transactional
//    public void saveReply(Long id, ReplyDTO replyDTO, User user) {
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
//        );
//
//        Reply reply = Reply.builder()
//                .board(board)
//                .content(replyDTO.getContent())
//                .user(user)
//                .build();
//
//        replyRepository.save(reply);
//
//    }

    public List<CommentResponseDto> getList(Long postId){


//        return commentRepository.findAllByOrderByCreatedAtDesc().stream().map(comment -> comment.toDto())//board를 dto로 변환 stream.map:list
//                .collect(Collectors.toList());
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        List<CommentGetResponseDto> commentGetResponseDtoList = new ArrayList<CommentGetResponseDto>();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findAllByPost(post);
        for (Comment comment : commentList) {
            commentResponseDtoList.add(comment.tocommentResponseDto());
        }

        return commentResponseDtoList;

    }

    @Transactional
    public Comment saveComment(Long postId, CommentRequestDto commentRequestDto, User user) {//저 id는 post id임 포스트연결할때 윗주석쓰기
    //public Comment saveComment(CommentRequestDto commentRequestDto) {//저 id는 post id임

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
        );

        Comment comment = Comment.builder()

                .post(post)
                .comment(commentRequestDto.getComment())
                .user(user)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }


    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto) {

        Comment commentEntity = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id = " + commentId)
        );

        commentEntity.update(commentRequestDto);

    }
}
