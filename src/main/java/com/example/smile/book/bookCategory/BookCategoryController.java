package com.example.smile.book.bookCategory;

import com.example.smile.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// 특정 카테고리에 속하는 도서 목록을 반환하는 역할
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    //카테고리별 도서 목록 반환
    @GetMapping("/books/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {
        return bookCategoryService.getBooksByCategory(category);
    }
}
