package com.example.smile.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initFirebase() throws IOException {
        // 환경 변수에서 Firebase 서비스 키 경로를 읽어옴
        String firebaseKeyPath = System.getenv("FIREBASE_KEY_PATH");

        if (firebaseKeyPath == null || firebaseKeyPath.isEmpty()) {
            throw new IllegalStateException("환경 변수 FIREBASE_KEY_PATH가 설정되어 있지 않습니다.");
        }

        FileInputStream serviceAccount = new FileInputStream(firebaseKeyPath);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // 이미 초기화된 앱이 없을 때만 초기화
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            System.out.println("✅ Firebase has been initialized successfully!");
        }
    }
}
