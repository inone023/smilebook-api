package com.example.smile.member;

import com.example.smile.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
// 회원 생성, 회원의 대출 및 예약 도서 조회 등의 기능을 제공하는 서비스 클래스
public class MemberService {

    private final MemberRepository memberRepository;

    //member 테이블에 회원 정보 create
    public void create(String memberId, String nickname, String password, String email, String phoneNumber) {
        Member member = new Member();
        member.setMemberId(memberId);
        member.setNickname(nickname);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);

        // BCryptPasswordEncoder로 비밀번호 해싱
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        // BCrypt 해시 60글자 제한
        String truncatedPassword = hashedPassword.substring(0, Math.min(60, hashedPassword.length()));

        member.setPassword(truncatedPassword);
        this.memberRepository.save(member);
    }

    // 특정 회원이 대출 중인 도서 목록을 조회하는 메서드
    public List<Book> getBorrowedBooks(String memberId) {
        return memberRepository.findBooksByMemberIdAndBookStatus(memberId, "대출 중");
    }

    // 특정 회원이 예약 중인 도서 목록을 조회하는 메서드
    public List<Book> getReservedBooks(String memberId) {
        return memberRepository.findBooksByMemberIdAndBookStatus(memberId, "예약 중");
    }

    // 특정 회원이 대출 중이거나 예약 중인 모든 도서 목록을 조회하는 메서드
    public List<Book> getAllBooks(String memberId) {
        List<Book> borrowedBooks = getBorrowedBooks(memberId);
        List<Book> reservedBooks = getReservedBooks(memberId);
        borrowedBooks.addAll(reservedBooks);
        return borrowedBooks;
    }
}
