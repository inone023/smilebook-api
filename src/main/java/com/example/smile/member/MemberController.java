package com.example.smile.member;

import com.example.smile.book.Book;
import com.example.smile.model.MemberDTO;
import com.example.smile.model.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
// "/member/**"의 url 들은 모두 로그인 인증 필요 없음
// 회원과 관련된 HTTP 요청을 처리하는 REST 컨트롤러 클래스.
// 회원 가입과 특정 회원의 도서 대출 목록을 조회하는 기능을 제공함.
public class MemberController {

    private final MemberService memberService;

    // 회원가입 요청을 처리하는 메서드
    @PostMapping("/join") // "/member/join" 회원가입 요청 처리
    public ResponseEntity<ResponseDTO> join(@RequestBody MemberDTO memberDTO) {
        memberService.create(memberDTO.getMemberId(), memberDTO.getNickname(), memberDTO.getPassword(), memberDTO.getEmail(), memberDTO.getPhoneNumber());
        return ResponseEntity.ok(new ResponseDTO(200, "회원가입 완료"));
    }

    // 특정 회원이 대출한 도서 목록을 조회하는 메서드
    @GetMapping("/{memberId}/borrowed-books")
    public ResponseEntity<List<Book>> getBorrowedBooksForMember(@PathVariable String memberId) {
        List<Book> borrowedBooks = memberService.getBorrowedBooks(memberId);
        return ResponseEntity.ok(borrowedBooks);
    }

    // 특정 회원이 대출 또는 예약한 모든 도서 목록을 조회하는 메서드
    @GetMapping("/{memberId}/all-books")
    public ResponseEntity<List<Book>> getAllBooksForMember(@PathVariable String memberId) {
        List<Book> allBooks = memberService.getAllBooks(memberId);
        return ResponseEntity.ok(allBooks);
    }
}
