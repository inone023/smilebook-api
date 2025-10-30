package com.example.smile.book.bookLocation;

import com.example.smile.book.Book;
import com.example.smile.model.BookLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//책의 위치 정보를 제공하는 컨트롤러 클래스
public class BookLocationController {

    private final BookLocationService bookLocationService;

    @Autowired
    public BookLocationController(BookLocationService bookLocationService) {
        this.bookLocationService = bookLocationService;
    }

    //책의 위치 정보를 조회하는 메서드
    @GetMapping("/books/{bookId}/location")
    public ResponseEntity<BookLocationDTO> getBookLocation(@PathVariable("bookId") Book bookId) {
        BookLocationDTO bookLocationDTO = bookLocationService.getBookLocationByBookId(bookId);
        if (bookLocationDTO != null) {
            return new ResponseEntity<>(bookLocationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}