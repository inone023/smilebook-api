package com.example.smile.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FCMService {
    //Firebase 클라우드 메시징 서비스를 사용하여 푸시 알림을 보내는 서비스 클래스

    // 특정 기기로 푸시 알림을 보내는 메서드
    public void sendPushNotification(String targetDeviceToken, String title, String body) {
        try {
            // Firebase 앱이 이미 초기화되었는지 확인
            if (FirebaseApp.getApps().isEmpty()) {
                // Firebase 서비스 키를 가져와서 Firebase 앱을 초기화
                InputStream serviceAccount = getClass().getResourceAsStream("/firebase/firebase_service_key.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }

            // Firebase 메시징을 통해 메시지 보내기
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(targetDeviceToken)
                    .build();

            // 생성된 메시지를 Firebase 메시징을 사용하여 전송
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            //예외 발생 시 에러 메시지 출력
            System.err.println("Error sending FCM message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
