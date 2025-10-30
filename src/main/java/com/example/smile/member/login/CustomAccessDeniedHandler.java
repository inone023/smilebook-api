package com.example.smile.member.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
// 사용자의 액세스 거부 처리를 담당하는 클래스
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    //요청 거부 메시지를 반환
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "액세스 요청 거부됨");
    }

}
