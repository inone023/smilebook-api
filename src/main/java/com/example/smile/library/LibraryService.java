package com.example.smile.library;

import com.example.smile.book.Book;
import com.example.smile.book.bookCategory.BookCategory;
import com.example.smile.book.bookCategory.BookCategoryRepository;
import com.example.smile.book.BookRepository;
import com.example.smile.firebase.FCMService;
import com.example.smile.member.Member;
import com.example.smile.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
// 도서 대출 및 반납 관련 로직을 처리하는 서비스 클래스
public class LibraryService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private FCMService fcmService;

    // 도서 대출 처리 메서드
    public String borrowBook(BorrowRequest request) {
        String memberRFID = request.getMemberRFID();
        String bookRFID = request.getBookRFID();

        // 회원 카드의 RFID로 회원 조회
        Member member = memberRepository.findBymemberRfidIgnoreCase(memberRFID);
        if (member == null) {
            // 회원을 찾을 수 없는 경우 푸시 알림 전송
            fcmService.sendPushNotification(memberRFID, "대출 실패", "회원을 찾을 수 없습니다.");
            return "대출 실패: 회원을 찾을 수 없습니다.";
        }

        // 도서 카드의 RFID로 도서 조회
        Book book = bookRepository.findBybookRfidIgnoreCase(bookRFID);
        if (book == null) {
            // 도서를 찾을 수 없는 경우 푸시 알림 전송
            fcmService.sendPushNotification(member.getFcmToken(), "대출 실패", "도서를 찾을 수 없습니다.");
            return "대출 실패: 도서를 찾을 수 없습니다.";
        }

        // 도서 상태 확인 및 대출 중인 경우 예약자 확인
        if (book.getBookStatus().equals("대출 중")) {
            if (book.getMember().getMemberId().equals(member.getMemberId())) {
                // 예약자와 대출하려는 회원이 일치하는 경우 반납 처리 호출
                ReturnRequest returnRequest = new ReturnRequest();
                returnRequest.setBookRFID(bookRFID);
                return returnBook(returnRequest);
            } else {
                // 해당 도서가 이미 대출 중인 경우 푸시 알림 전송
                fcmService.sendPushNotification(member.getFcmToken(), "대출 실패", "해당 도서는 이미 대출 중입니다.");
                return "대출 실패: 해당 도서는 이미 대출 중입니다.";
            }
        }

        // 대출 처리
        book.setMember(member);
        book.setBookStatus("대출 중");
        book.setLoanDate(LocalDateTime.now());
        book.setDueDate(LocalDateTime.now().plusDays(14));
        bookRepository.save(book);

        // 사용자에게 푸시 알림 전송 및 대출 성공 메시지 반환
        fcmService.sendPushNotification(member.getFcmToken(), "도서 대출 알림", "도서가 성공적으로 대출되었습니다.");

        return "대출 성공";
    }

    //도서 반납 처리 메서드
    public String returnBook(ReturnRequest request) {
        String bookRFID = request.getBookRFID();

        // 도서 카드의 RFID로 도서 조회
        Book book = bookRepository.findBybookRfidIgnoreCase(bookRFID);
        if (book == null) {
            // 도서를 찾을 수 없는 경우 푸시 알림 전송
            fcmService.sendPushNotification(bookRFID, "반납 실패", "도서를 찾을 수 없습니다.");
            return "반납 실패: 도서를 찾을 수 없습니다.";
        }

        // 대출 중인 도서인지 확인
        if (!book.getBookStatus().equals("대출 중")) {
            // 대출 중이 아닌 경우 푸시 알림 전송
            fcmService.sendPushNotification(book.getMember().getFcmToken(), "반납 실패", "해당 도서는 대출 중이 아닙니다.");
            return "반납 실패: 해당 도서는 대출 중이 아닙니다.";
        }

        // BookCategory 엔티티에서 해당 도서의 좌표 업데이트
        BookCategory bookCategory = bookCategoryRepository.findByBookId(book);
        if (bookCategory != null) {
            bookCategory.setXCoordinate(360f);
            bookCategory.setYCoordinate(550f);
            bookCategoryRepository.save(bookCategory);
        }

        // 대출 중인 회원 정보 삭제 및 도서 상태 변경
        Member member = book.getMember(); // 대출한 회원 정보 가져오기
        book.setMember(null);
        book.setBookStatus("대출가능");
        bookRepository.save(book);

        //사용자에게 푸시 알림 전송 및 반납 성공 메시지 반환
        fcmService.sendPushNotification(member.getFcmToken(), "반납 성공", "도서가 성공적으로 반납 되었습니다.");
        return "반납 성공";
    }
}
