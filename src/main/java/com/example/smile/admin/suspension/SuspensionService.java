package com.example.smile.admin.suspension;

import com.example.smile.firebase.FCMService;
import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import com.example.smile.model.SuspensionReasonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
// 회원의 정지 상태를 관리하는 서비스 클래스
public class SuspensionService {

    private final MemberRepository memberRepository;
    private final FCMService fcmService;

    @Autowired
    public SuspensionService(MemberRepository memberRepository, FCMService fcmService) {
        this.memberRepository = memberRepository;
        this.fcmService = fcmService;
    }

    // 회원의 정지 사유를 업데이트하는 메서드
    public ResponseEntity<?> updateSuspensionReason(SuspensionReasonDTO suspensionReasonDTO) {
        try {
            //회원 조회 
            Member member = memberRepository.findById(suspensionReasonDTO.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("Member not found"));

            // 현재 회원의 상태에 따라 상태 변경
            if ("이용 정지 대상자".equals(member.getMemberStatus())) {
                member.setMemberStatus("이용 가능한 사용자");
            } else {
                member.setMemberStatus("이용 정지 대상자");

                // 사용자에게 푸시 알림 전송
                fcmService.sendPushNotification(member.getFcmToken(), "이용 정지 안내", "이용이 정지되었습니다.");
            }

            //회원의 정지 사유를 설정하고 저장
            member.setSuspensionReason(suspensionReasonDTO.getSuspensionReason());
            memberRepository.save(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            //예외 발생 시 서버 오류 응답 반환
            System.err.println("Error updating suspension reason: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
