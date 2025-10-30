package com.example.smile.member.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

// 사용자의 인증을 담당하는 커스텀 필터 클래스
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    // "/api/login" url 에서 해당 필터 동작
    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/login"));
    }

    // 사용자의 로그인 인증을 시도하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if(!isPost(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        //해당 요청이 POST 일 때 body를 DTO에 매핑
        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);

        // Id, Password 의 존재 여부 확인
        if(!StringUtils.hasLength(accountDto.getUsername())
                || !StringUtils.hasLength(accountDto.getPassword())) {
            throw new IllegalArgumentException("username or password is empty");
        }

        // 인증 되지 않은 토큰 생성
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                accountDto.getUsername(),
                accountDto.getPassword()
        );

        // Manager 에게 인증 처리 받음
        Authentication authenticate = getAuthenticationManager().authenticate(token);

        return authenticate;
    }

    // HTTP 요청이 POST 방식인지 확인하는 메서드
    private boolean isPost(HttpServletRequest request) {

        if("POST".equals(request.getMethod())) {
            return true;
        }

        return false;
    }

    // 사용자의 ID와 Password를 저장하는 DTO 클래스
    @Data
    public static class AccountDto {
        private String username;
        private String password;
    }

}