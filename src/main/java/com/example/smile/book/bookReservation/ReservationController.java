package com.example.smile.book.bookReservation;

import com.example.smile.model.ReservationDTO;
import com.example.smile.model.ReservationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
// 도서 예약 관련 기능을 처리하는 컨트롤러 클래스
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 도서를 예약하는 메서드
    @PostMapping("/reserve")
    public ReservationResponseDTO reserveBook(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.reserveBook(reservationDTO);
    }
}