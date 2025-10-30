package com.example.smile.model;

import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter

//서버 응답 DTO
public class ResponseDTO {
    private int code;
    private String message;



    public ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


