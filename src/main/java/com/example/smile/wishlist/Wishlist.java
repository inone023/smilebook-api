package com.example.smile.wishlist;

import com.example.smile.book.Book;
import com.example.smile.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "wishlist")
// 찜 도서 앤티티 클래스, 데이터베이스 테이블 'wishlist'를 매핑함
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId", referencedColumnName ="memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName ="bookId")
    private Book book;
}
