package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.CommentRequestDto;
import com.sparta.miniproject.dto.CommentResponseDto;
import com.sparta.miniproject.security.UserDetailsImpl;
import com.sparta.miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto> getComment(@PathVariable Long postId){
        return commentService.getList(postId);

    }

    @PostMapping("/comment/{postId}")
    public CommentRequestDto saveComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    //public CommentResponseDto saveComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {//1
    //public CommentResponseDto saveComment(@RequestBody CommentRequestDto commentRequestDto) {//2


        commentService.saveComment(postId, commentRequestDto, userDetailsImpl.getUser());
        //commentService.saveComment(id, commentRequestDto);//1
        //commentService.saveComment(commentRequestDto);//2

        //return commentService.saveComment(id, commentRequestDto);

        //return new CommentResponseDto(HttpStatus.CREATED.value(), "댓글 작성이 완료되었습니다.", null);

        return new CommentRequestDto();
    }
//댓글 예시
//    @PostMapping("/posts/{id}/reply")
//    public ResponseDTO saveReply(@PathVariable Long id,
//                                 @RequestBody ReplyDTO replyDTO,
//                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
//
//        boardService.saveReply(id, replyDTO, principalDetails.getUser());
//
//        return new ResponseDTO(HttpStatus.CREATED.value(), "댓글 작성이 완료되었습니다.", null);
//    }


    @PutMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {

        commentService.updateComment(commentId, commentRequestDto);

        return new CommentResponseDto();

        //return new CommentResponseDto(HttpStatus.OK.value(), "댓글이 수정되었습니다.", null);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return commentId;
    }
}
