package com.example.smile.wishlist;

import com.example.smile.book.Book;
import com.example.smile.member.Member;
import com.example.smile.model.WishlistDTO;
import com.example.smile.model.WishlistItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 찜 아이템 추가, 삭제 및 조회 기능 등 찜 목록과 관련된 비즈니스 로직을 처리하는 서비스 클래스
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // 찜 목록에 아이템을 추가하는 메서드
    public void addToWishlist(String memberId, Long bookId) {
        Wishlist wishlist = new Wishlist();
        Member member = new Member();
        member.setMemberId(memberId);
        Book book = new Book();
        book.setBookId(bookId);

        wishlist.setMember(member);
        wishlist.setBook(book);

        wishlistRepository.save(wishlist);
    }

    // 찜 목록에서 아이템을 삭제하는 메서드
    public void deleteFromWishlist(String memberId, Long bookId) {
        Wishlist wishlist = wishlistRepository.findByMemberMemberIdAndBookBookId(memberId, bookId);
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
        }
    }

    // 특정 회원의 찜 목록 불러오는 메서드
    public WishlistItemDTO getWishlistByMemberId(String memberId) {
        List<Wishlist> wishlist = wishlistRepository.findByMemberMemberId(memberId);
        if (!wishlist.isEmpty()) {
            WishlistItemDTO wishlistItemDTO = new WishlistItemDTO();
            for (Wishlist item : wishlist) {
                wishlistItemDTO.addBookId(item.getBook().getBookId());
            }
            wishlistItemDTO.setMemberId(memberId);
            return wishlistItemDTO;
        } else {
            return null;
        }
    }

    // 특정 회원이 특정 도서를 찜 목록에 가지고 있는지 확인하는 메서드
    public WishlistDTO getWishlist(WishlistDTO wishlistDTO) {
        Wishlist existingWishlist = wishlistRepository.findByMemberMemberIdAndBookBookId(wishlistDTO.getMemberId(), wishlistDTO.getBookId());
        if (existingWishlist != null) {
            // 찜 목록이 존재하는 경우
            Member member = existingWishlist.getMember();
            Book book = existingWishlist.getBook();

            WishlistDTO result = new WishlistDTO();
            result.setMemberId(member.getMemberId());
            result.setBookId(book.getBookId());
            return result;
        } else {
            // 찜 목록이 존재하지 않을 경우
            return null;
        }
    }
}