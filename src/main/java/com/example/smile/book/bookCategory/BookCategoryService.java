package com.example.smile.book.bookCategory;

import com.example.smile.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// BookCategoryRepository를 사용하여 카테고리별 도서 목록을 제공하는 기능을 수행한다.
@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    // 주어진 카테고리에 속하는 도서 목록을 조회하고 반환
    public List<Book> getBooksByCategory(String category) {
        return bookCategoryRepository.findBooksByCategory(category);
    }
}
