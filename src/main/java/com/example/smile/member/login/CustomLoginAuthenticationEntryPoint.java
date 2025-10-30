package com.example.smile.member.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
// 사용자의 로그인 인증 진입점을 처리하는 클래스
public class CustomLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    //인증이 필요한 리소스에 인증되지 않은 사용자가 접근했을 때 실행
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증 안된 요청");
        // HTTP 상태 코드 401 설정
    }
}
