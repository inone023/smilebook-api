package com.example.smile.book;

import com.example.smile.book.bookCategory.BookCategory;
import com.example.smile.book.bookCategory.BookCategoryRepository;
import com.example.smile.model.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 모든 도서 불러오기
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // bookId를 기반으로 도서 정보 가져오기
    public Book getBookById(Long bookId) {
        //DB에서 특정 bookId에 해당하는 도서 조회, 해당 도서가 존재하지 않을 경우 null 반환
        return bookRepository.findById(bookId).orElse(null);
    }

    // 도서 제목 검색 메서드
    public List<Book> searchBooksByTitle(String query) {
        return bookRepository.findBybookTitleContaining(query);
    }

    // 도서 등록 메서드
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookId(bookDTO.getBookId());
        book.setBookRfid(bookDTO.getBookRfid());
        book.setBookTitle(bookDTO.getBookTitle());
        book.setCoverUrl(bookDTO.getCoverUrl());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setMemo(bookDTO.getMemo());
        book.setDescription(bookDTO.getDescription());
        book.setContents(bookDTO.getContents());
        book.setBookStatus(bookDTO.getBookStatus());

        Book savedBook = bookRepository.save(book);

        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookId(savedBook);
        bookCategory.setCategory(bookDTO.getCategory());

        bookCategoryRepository.save(bookCategory);

        return savedBook;
    }

    // 대출 연장
    public String extendLoan(Long bookId) {
        // bookId를 기반으로 해당 도서 정보 조회
        Book book = bookRepository.findById(bookId).orElse(null);
        if (null != book) {
            if (book.getBookStatus().equals("대출 중")) {
                // 대출 중인 경우에만 연장 가능하도록 처리
                LocalDateTime dueDate = book.getDueDate();
                book.setDueDate(dueDate.plusDays(7)); // 반납일을 7일 연장
                bookRepository.save(book);
                return "연장 성공";
            } else {
                return "도서가 대출 중이 아닙니다.";
            }
        } else {
            return "도서를 찾을 수 없습니다.";
        }
    }
}