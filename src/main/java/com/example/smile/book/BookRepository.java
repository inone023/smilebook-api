package com.example.smile.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBybookTitleContaining(String query);

    //대출 및 반납
    Book findBybookRfidIgnoreCase(String bookRFID);
}