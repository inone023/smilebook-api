package com.example.smile.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
// 도서 대출 및 반납 관련 기능을 처리하는 컨트롤러 클래스
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // 도서를 대출하는 메서드
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest request) {
        String result = libraryService.borrowBook(request);
        return ResponseEntity.ok(result);
    }

    // 도서를 반납하는 메서드
    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody ReturnRequest request) {
        String result = libraryService.returnBook(request);
        return ResponseEntity.ok(result);
    }
}