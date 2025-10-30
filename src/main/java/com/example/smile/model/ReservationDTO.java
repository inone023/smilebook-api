package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ReservationDTO {
    private String memberId;
    private Long bookId;

    public ReservationDTO(String memberId, Long bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }
}
