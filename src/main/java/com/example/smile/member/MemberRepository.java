package com.example.smile.member;

import com.example.smile.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//member 데이터베이스 테이블과 맵핑된 Member 엔티티에 대한 CRUD(Create, Read, Update, Delete)작업 수행
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberId(String memberId);

    //대출 및 반납
    Member findBymemberRfidIgnoreCase(String RFIDCardId);
    @Query("SELECT b FROM Book b WHERE b.member.memberId = :memberId AND b.bookStatus = :bookStatus")
    List<Book> findBooksByMemberIdAndBookStatus(@Param("memberId") String memberId, @Param("bookStatus") String bookStatus);
}
