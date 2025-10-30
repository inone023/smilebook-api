package com.example.smile.member.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
// 사용자의 로그인 실패 처리를 담당하는 클래스
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    // 로그인 실패 시 실행
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String errMsg = "Invalid Username or Password";

        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // HTTP 상태 코드를 401로 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 응답을 JSON 으로 설정

        //예외상황에 따른 에러메시지 생성
        if (exception instanceof BadCredentialsException) {
            errMsg = "Username 또는 Password 불일치";
        } else if (exception instanceof DisabledException) {
            errMsg = "계정 비활성화";
        } else if (exception instanceof CredentialsExpiredException) {
            errMsg = "사용자 비밀번호 만료";
        } else if (exception instanceof SuspendedMemberException) {
            errMsg = exception.getMessage();
        }

        objectMapper.writeValue(response.getWriter(), errMsg);
    }
}
