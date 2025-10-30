package com.example.smile.member.login;


import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 사용자의 상세 정보를 로드하는 클래스
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // DB에서 사용자 ID를 조회하여 UserDetails 객체를 반환
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberId(userid)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return member;
    }
}

