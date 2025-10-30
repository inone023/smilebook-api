package com.example.smile.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FCMService {
    // FirebaseConfig에서 이미 FirebaseApp이 초기화되므로 여기서 다시 초기화할 필요 없음

    public void sendPushNotification(String targetDeviceToken, String title, String body) {
        try {
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(targetDeviceToken)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            System.err.println("Error sending FCM message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
