package com.example.smile.book.bookCategory;

import com.example.smile.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// BookCategory 엔티티에 대한 데이터 접근을 처리하는 인터페이스
public interface BookCategoryRepository extends JpaRepository<BookCategory, Book> {
    BookCategory findByBookId(Book bookId);

    // 주어진 카테고리에 해당하는 도서 ID 목록을 반환한다.
    @Query("SELECT bc.bookId FROM BookCategory bc WHERE bc.category = :category")
    List<Book> findBooksByCategory(String category);
}