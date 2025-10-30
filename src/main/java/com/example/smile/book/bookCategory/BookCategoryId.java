package com.example.smile.book.bookCategory;

import java.io.Serializable;
import java.util.Objects;

// 복합 기본 키를 나타내기 위해 사용되는 클래스
public class BookCategoryId implements Serializable {
    private String category;
    private Long bookId;

    public BookCategoryId() {}

    public BookCategoryId(String category, Long bookId) {
        this.category = category;
        this.bookId = bookId;
    }

    // 두 객체가 동일한지를 비교하는 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategoryId that = (BookCategoryId) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(bookId, that.bookId);
    }

    // 객체의 해시 코드를 반환하는 메서드
    @Override
    public int hashCode() {
        return Objects.hash(category, bookId);
    }
}