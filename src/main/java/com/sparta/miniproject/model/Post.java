//package com.sparta.miniproject.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Entity
//public class Post extends Timestamped {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String title;
//
//    private int count; // TODO 조회수 할 때 사용
//
//    @Column
//    private String content;
//
////    @ManyToOne
////    @JoinColumn(name = "userId")
////    private User user;
//
////    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
////    @JsonIgnoreProperties({"board"}) // 무한참조 방지
////    @OrderBy("id desc")
////    private List<Comment> comments;
//}
