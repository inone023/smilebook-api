package com.example.smile.admin.userData;

import com.example.smile.firebase.FCMService;
import com.example.smile.member.Member;
import com.example.smile.model.UserDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//회원 데이터 처리 서비스 클래스
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private FCMService fcmService; // FCMService 주입

    //회원 정보 조회 메서드
    public UserDataDTO getMemberInfo(String memberId) {
        Member member = userDataRepository.findByMemberId(memberId);
        if (member != null) {
            return new UserDataDTO(member.getMemberId(), member.getMemberRfid(), member.getMemberStatus(), member.getWarningCount());
        }
        return null;
    }

    // 회원에게 푸시 알림을 전송하는 메서드
    public void sendPushNotification(String memberId, String title, String body) {
        Member member = userDataRepository.findByMemberId(memberId);
        if (member != null) {
            String fcmToken = member.getFcmToken();
            if (fcmToken != null && !fcmToken.isEmpty()) {
                // 회원의 토큰을 사용하여 FCMService를 통해 푸시 알림을 보냄
                fcmService.sendPushNotification(fcmToken, title, body);
            } else {
                System.err.println("FCM 토큰이 없습니다.");
            }
        } else {
            System.err.println("해당하는 회원이 없습니다.");
        }
    }

    // 푸시 알림 테스트 메서드
    public void testPushNotification(String memberId) {
        sendPushNotification(memberId, "푸시 알림 테스트", "이것은 푸시 알림 테스트입니다.");
    }
}
