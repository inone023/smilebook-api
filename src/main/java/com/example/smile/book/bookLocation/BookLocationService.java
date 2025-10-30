package com.example.smile.book.bookLocation;

import com.example.smile.book.Book;
import com.example.smile.book.bookCategory.BookCategory;
import com.example.smile.book.bookCategory.BookCategoryRepository;
import com.example.smile.model.BookLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 책의 위치 정보를 처리하는 서비스 클래스
public class BookLocationService {
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookLocationService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }


    // BookLocation 엔티티에서 bookId로 조회하여 값을 가져옴
    public BookLocationDTO getBookLocationByBookId(Book bookId) {
        BookCategory bookCategory = bookCategoryRepository.findByBookId(bookId);
        if (bookCategory!= null) {
            BookLocationDTO bookLocationDTO = new BookLocationDTO();
            bookLocationDTO.setFloor(bookCategory.getFloor());
            bookLocationDTO.setXCoordinate(bookCategory.getXCoordinate());
            bookLocationDTO.setYCoordinate(bookCategory.getYCoordinate());
            bookLocationDTO.setCategory(bookCategory.getCategory());

            return bookLocationDTO;
        } else {
            return null;
        }
    }
}