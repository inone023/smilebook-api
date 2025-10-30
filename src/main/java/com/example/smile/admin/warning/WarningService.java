package com.example.smile.admin.warning;

import com.example.smile.firebase.FCMService;
import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import com.example.smile.model.SuspensionReasonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//회원 경고 기능을 처리하는 서비스 클래스
@Service
public class WarningService {
    @Autowired
    private MemberRepository memberRepository;

    private final FCMService fcmService;

    @Autowired
    public WarningService(MemberRepository memberRepository, FCMService fcmService) {
        this.memberRepository = memberRepository;
        this.fcmService = fcmService;
    }

    // 회원의 경고 정보를 업데이트하는 메서드
    public void updateWarning(SuspensionReasonDTO suspensionReasonDTO) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(suspensionReasonDTO.getMemberId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            int warningCount = member.getWarningCount();
            if (warningCount < 3) {
                member.setWarningCount(warningCount + 1);
                member.setWarningReason(suspensionReasonDTO.getSuspensionReason());
                // 사용자에게 푸시 알림 전송
                fcmService.sendPushNotification(member.getFcmToken(), "이용 경고 안내", "경고 1회 추가 되었습니다.");
                memberRepository.save(member);
                if (warningCount + 1 == 3) {
                    member.setMemberStatus("이용 정지 대상자");

                    // 사용자에게 푸시 알림 전송
                    fcmService.sendPushNotification(member.getFcmToken(), "경고 3회 누적", "이용 정지 처리되었습니다.");
                    memberRepository.save(member);

                }
            }
        } else {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }
    }

}

