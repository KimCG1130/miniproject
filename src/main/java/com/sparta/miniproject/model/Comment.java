package com.sparta.miniproject.model;

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

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime commentDate;


//    @ManyToOne
//    @JoinColumn(name = "postId")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "nickname")
//    private User user;


    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
