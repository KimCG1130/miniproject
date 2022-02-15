package com.sparta.miniproject.model;

import com.sparta.miniproject.Post.Post;
import com.sparta.miniproject.dto.CommentRequestDto;
import com.sparta.miniproject.model.Timestamped;
import com.sparta.miniproject.repository.CommentRepository;
//import com.sparta.miniproject.model.Post;
import com.sparta.miniproject.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String comment;

//    @CreatedDate
//    @Column(name="created_at")
//    private LocalDateTime commentDate;


    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private User user;


    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }



    public Comment toResponseDto() { //보드를 디티오로 바꿔서 반환해주는 메서드(자세한건 서비스에 기술되어있음)*
        return Comment.builder()

                .comment(this.comment)
                //.commentDate(this.getCommentDate())
                .build();
    }
}
