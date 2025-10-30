package com.example.smile.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//특정 회원의 찜 목록
@Data
public class WishlistItemDTO {
    private String memberId;
    private List<Long> bookIds;

    public WishlistItemDTO() {
        this.bookIds = new ArrayList<>();
    }

    public void addBookId(Long bookId) {
        this.bookIds.add(bookId);
    }
}