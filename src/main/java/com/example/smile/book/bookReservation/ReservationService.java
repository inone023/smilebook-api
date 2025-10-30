package com.example.smile.book.bookReservation;

import com.example.smile.book.Book;
import com.example.smile.member.Member;
import com.example.smile.model.ReservationDTO;
import com.example.smile.model.ReservationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//도서 예약 관련 로직을 처리하는 서비스 클래스
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 도서의 현재 상태에 따라 도서 상태 변경
    public ReservationResponseDTO reserveBook(ReservationDTO reservationDTO) {
        Book book = reservationRepository.findById(reservationDTO.getBookId()).orElse(null);
        if (book == null) {
            return new ReservationResponseDTO(false, "Book not found");
        }

        if ("대출가능".equals(book.getBookStatus())) {
            book.setBookStatus("예약 중");
            Member member = new Member();
            member.setMemberId(reservationDTO.getMemberId());
            book.setMember(member);
        } else if ("예약 중".equals(book.getBookStatus())) {
            // 예약 회원 확인
            if (book.getMember() != null && book.getMember().getMemberId().equals(reservationDTO.getMemberId())) {
                book.setBookStatus("대출가능");
                book.setMember(null);
            } else {
                // 예약 회원 불일치 시
                return new ReservationResponseDTO(false, "이미 다른 사용자에 의해 예약된 도서입니다.");
            }
        }

        reservationRepository.save(book);

        return new ReservationResponseDTO(true, "Book status updated successfully");
    }
}