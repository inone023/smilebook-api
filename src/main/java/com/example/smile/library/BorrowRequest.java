package com.example.smile.library;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class BorrowRequest {
    private String memberRFID;
    private String bookRFID;
}
