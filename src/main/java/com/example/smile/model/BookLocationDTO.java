package com.example.smile.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookLocationDTO {
    private String floor;
    private Float xCoordinate;
    private Float yCoordinate;
    private String category;
}
