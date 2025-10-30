package com.example.smile.member.myInfo;

import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import com.example.smile.model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// 사용자 정보 관련 비즈니스 로직을 처리하는 서비스 클래스
public class MyinfoService {

    @Autowired
    private MemberRepository memberRepository;

    // 회원 상세 정보를 가져오는 메서드
    public MemberDTO getMemberDetails(String memberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        Member member = optionalMember.orElse(null);

        if (member == null) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setNickname(member.getNickname());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhoneNumber(member.getPhoneNumber());

        return memberDTO;
    }

    // 회원 정보를 업데이트하는 메서드
    public boolean updateMemberInfo(MemberDTO request) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(request.getMemberId());

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            // 수정된 정보로 회원 정보 업데이트
            member.setNickname(request.getNickname());
            member.setEmail(request.getEmail());
            member.setPhoneNumber(request.getPhoneNumber());

            // BCryptPasswordEncoder로 비밀번호 해싱
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            // BCrypt 해시 60글자 제한
            String truncatedPassword = hashedPassword.substring(0, Math.min(60, hashedPassword.length()));

            member.setPassword(truncatedPassword);
            memberRepository.save(member);
            return true;
        } else {
            return false;
        }
    }
}
