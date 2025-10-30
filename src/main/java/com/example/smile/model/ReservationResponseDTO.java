package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ReservationResponseDTO {
    private boolean success;
    private String message;
    private String reservationTime;

    public ReservationResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.reservationTime = reservationTime;
    }
}
