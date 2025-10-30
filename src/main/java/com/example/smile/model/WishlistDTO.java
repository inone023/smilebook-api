package com.example.smile.model;

import lombok.Data;

//특정 회원의 특정 도서에 대한 찜 상태
@Data
public class WishlistDTO {
    private String memberId;
    private Long bookId;
}
