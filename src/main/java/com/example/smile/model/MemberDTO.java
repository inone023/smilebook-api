package com.example.smile.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//클라이언트 요청 처리 DTO
public class MemberDTO {
    private String memberId;
    private String nickname;
    private String password;
    private String email;
    private String phoneNumber;
    private String roles;
}



