package com.example.smile.book;

import com.example.smile.model.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
// 도서와 관련된 HTTP 요청을 처리하는 REST 컨트롤러 클래스
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //모든 도서 정보를 불러오는 메서드.
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (!books.isEmpty()) {
            return ResponseEntity.ok().body(books);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //bookId를 통해 특정 도서 정보를 불러오는 메서드
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            return ResponseEntity.ok().body(book);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // 책 제목을 검색하여 일치하는 도서 목록을 반환하는 메서드
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("query") String query) {
        return bookService.searchBooksByTitle(query);
    }

    // 새로운 도서를 등록하는 메서드
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book addedBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }

    // 대출 연장 메서드
    @PutMapping("/{bookId}/extendLoan")
    public ResponseEntity<String> extendLoan(@PathVariable Long bookId) {
        String message = bookService.extendLoan(bookId);
        if (message.equals("연장 성공")) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.badRequest().body(message);
        }
    }
}