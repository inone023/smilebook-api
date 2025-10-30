package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class TokenDTO {
    private String memberId;
    private String token;

    public TokenDTO(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
}
