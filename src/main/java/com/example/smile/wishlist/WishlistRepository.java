package com.example.smile.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // 특정 사용자의 찜 목록을 가져오는 쿼리 메서드
    List<Wishlist> findByMemberMemberId(String memberId);
    // 사용자의 찜 여부 반환
    Wishlist findByMemberMemberIdAndBookBookId(String memberId, Long bookId);
}
