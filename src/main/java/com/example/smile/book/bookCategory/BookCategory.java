package com.example.smile.book.bookCategory;

import com.example.smile.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book_category")
@IdClass(BookCategoryId.class)
// 도서 카테고리 앤티티 클래스, 데이터베이스 테이블 'book_category'를 매핑함
public class BookCategory {

    @Id
    @Column(name = "category")
    private String category;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book bookId;

    @Column(name = "floor")
    private String floor;

    @Column(name = "xCoordinate")
    private Float xCoordinate;

    @Column(name = "yCoordinate")
    private Float yCoordinate;
}