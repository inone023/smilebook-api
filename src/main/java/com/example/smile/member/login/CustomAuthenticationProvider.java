package com.example.smile.member.login;

import com.example.smile.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// 사용자의 인증을 담당하는 커스텀 프로바이더 클래스
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder; // 사용자가 입력한 정보를 암호화 한 뒤 DB와 비교

    //사용자의 인증을 처리하는 메서드
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 로그인 ID로 사용자 정보 조회
        Member member = (Member) userDetailsService.loadUserByUsername(loginId);

        // 사용자가 정지된 경우 예외를 발생
        if (member.getMemberStatus().equals("이용 정지 대상자")) {
            throw new SuspendedMemberException("이용 정지된 대상자입니다."); // 새로운 예외 던짐
        }

        // 패스워드 불일치 시 예외를 발생
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("Invalid Password"); // 패스워드 불일치
        }

        // 사용자 정보와 사용자의 권한 목록 반환
        return new CustomAuthenticationToken(member, null, member.getAuthorities());
    }

    // 해당 프로바이더가 특정 토큰을 지원하는지 확인하는 메서드
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
