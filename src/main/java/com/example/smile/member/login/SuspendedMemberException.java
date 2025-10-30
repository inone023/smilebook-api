package com.example.smile.member.login;

import org.springframework.security.core.AuthenticationException;

// 사용자가 정지된 경우 발생하는 예외 클래스
public class SuspendedMemberException extends AuthenticationException {
    public SuspendedMemberException(String msg) {
        super(msg);
    }
}
