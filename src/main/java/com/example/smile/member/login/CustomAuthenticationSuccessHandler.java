package com.example.smile.member.login;

import com.example.smile.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
// 사용자의 로그인 성공 처리를 담당하는 클래스
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    //로그인 성공 시 실행
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Member member =  (Member) authentication.getPrincipal(); //로그인한 사용자 식별

        response.setStatus(HttpStatus.OK.value()); //응답의 HTTP 상태 코드를 200으로 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //응답의 컨텐츠 타입을 JSON으로 설정

        objectMapper.writeValue(response.getWriter(), member); //객체를 JSON 형식으로 변환하여 응답으로 전송
    }
}