package com.example.smile.wishlist;

import com.example.smile.model.WishlistDTO;
import com.example.smile.model.WishlistItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
// 찜 목록과 관련된 HTTP 요청을 처리하는 컨트롤러 클래스
public class WishlistController {
    private final WishlistService wishlistService;

    // WishlistService를 주입받는 생성자
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // 찜 목록에 아이템을 추가하는 메서드
    @PostMapping("/wishlist/add")
    public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO request) {
        wishlistService.addToWishlist(request.getMemberId(), request.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added to wishlist successfully");
    }

    // 찜 목록에서 아이템을 삭제하는 메서드
    @DeleteMapping("/wishlist/delete/{memberId}/{bookId}")
    public ResponseEntity<String> deleteFromWishlist(@PathVariable String memberId, @PathVariable Long bookId) {
        wishlistService.deleteFromWishlist(memberId, bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from wishlist successfully");
    }

    //특정 회원의 찜 목록을 조회하는 메서드
    @PostMapping("/wishlist/{memberId}")
    public ResponseEntity<WishlistItemDTO> getWishlistByMemberId(@PathVariable String memberId) {
        WishlistItemDTO wishlistItemDTO = wishlistService.getWishlistByMemberId(memberId);
        if (wishlistItemDTO != null) {
            return ResponseEntity.ok(wishlistItemDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //특정 회원의 특정 도서에 대한 찜 여부를 조회하는 메서드
    @PostMapping("/wishlist/check")
    public ResponseEntity<WishlistDTO> checkWishlist(@RequestBody WishlistDTO wishlistDTO) {
        WishlistDTO result = wishlistService.getWishlist(wishlistDTO);
        if (result != null) {
            return ResponseEntity.ok(result); // 찜 목록이 있을 경우
        } else {
            return ResponseEntity.notFound().build(); // 찜 목록이 없을 경우
        }
    }
}
