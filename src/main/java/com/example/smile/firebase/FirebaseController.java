package com.example.smile.firebase;

import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import com.example.smile.model.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firebase")
// Firebase 관련 요청을 처리하는 컨트롤러 클래스
public class FirebaseController {

    @Autowired
    private MemberRepository memberRepository;

    // 사용자로부터 받은 Firebase 토큰을 저장하는 메서드
    @PostMapping("/token")
    public ResponseEntity<?> receiveFirebaseToken(@RequestBody TokenDTO tokenDTO) {
        try {
            // 사용자 ID를 기반으로 회원을 조회
            Member member = memberRepository.findById(tokenDTO.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("Member not found"));

            // 회원의 Firebase 토큰을 업데이트하고 저장
            member.setFcmToken(tokenDTO.getToken());
            memberRepository.save(member);

            // 성공적으로 업데이트되었음을 응답
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 발생 시 서버 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}