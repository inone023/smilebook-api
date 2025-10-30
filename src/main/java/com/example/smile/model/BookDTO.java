package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookDTO {
    private Long bookId;
    private String bookRfid;
    private String bookTitle;
    private String coverUrl;
    private String author;
    private String publisher;
    private String memo;
    private String description;
    private String contents;
    private String bookStatus;
    private String reservationTime;
    private String loanDate;
    private String dueDate;
    private String member;
    private String category; // 새로 추가된 필드
}
