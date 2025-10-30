package com.example.smile.book;

import com.example.smile.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name = "book")
// 도서 앤티티 클래스, 데이터베이스 테이블 'book'을 매핑함
public class Book {
    @Id
    @Column(name = "bookId", length = 255, nullable = false)
    private Long bookId;

    @Column(name = "bookRFID", length = 255)
    private String bookRfid;

    @Column(name = "bookTitle", length = 255)
    private String bookTitle;

    @Column(name = "coverUrl", length = 255)
    private String coverUrl;

    @Column(name = "author", length = 255)
    private String author;

    @Column(name = "publisher", length = 255)
    private String publisher;

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "bookStatus", length = 45, nullable = false)
    private String bookStatus;

    @Column(name = "reservationTime")
    private LocalDateTime reservationTime;

    @Column(name = "loanDate")
    private LocalDateTime loanDate;

    @Column(name = "dueDate")
    private LocalDateTime dueDate;

    @JsonIgnore // JSON 직렬화 시 무한 루프 방지
    @ManyToOne
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;

}
