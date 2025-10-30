package com.example.smile.book.bookReservation;

import com.example.smile.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Book, Long> {
}
