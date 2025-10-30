package com.example.smile.book;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
// 이미지 파일과 관련된 HTTP 요청을 처리하는 컨트롤러 클래스
public class ImageController {

    // 이미지 파일을 불러와 클라이언트에게 반환하는 메서드
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        // 웹 서버에 저장된 이미지 경로
        String imagePath = "/home/ubuntu/bookCover/" + imageName;

        // 이미지를 바이트 배열로 읽어옴
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        // 바이트 배열로부터 ByteArrayResource 생성, Resource로 래핑
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        // 이미지 응답 반환
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}