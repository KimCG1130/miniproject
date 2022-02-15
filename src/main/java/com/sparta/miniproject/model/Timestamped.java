//package com.sparta.miniproject.model;
//
//import lombok.Getter;
//<<<<<<< HEAD
//import org.hibernate.annotations.CreationTimestamp;
//=======
//import org.springframework.data.annotation.CreatedDate;
//>>>>>>> a3cecb6e9d0fde7049fcdb906cee5608676c71c0
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//<<<<<<< HEAD
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//@Getter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class Timestamped {
//
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    @CreationTimestamp
//    private Timestamp commentDate;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;
//}
//=======
//import java.time.LocalDateTime;
//
//@Getter
//@MappedSuperclass // Entity 가 자동으로 컬럼으로 인식합니다.
//@EntityListeners(AuditingEntityListener.class) // 내부 클래스가 변경 될때 마다 자동으로 업데이트합니다.
//public abstract class Timestamped {
//    @CreatedDate    // 생성 시간
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate   // 수정 시간
//    private LocalDateTime modifiedAt;
//}
//>>>>>>> a3cecb6e9d0fde7049fcdb906cee5608676c71c0
