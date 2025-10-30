package com.example.smile.member.myInfo;

import com.example.smile.model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
// 사용자 정보 관련 엔드포인트를 처리하는 컨트롤러 클래스
public class MyInfoController {

    @Autowired
    private MyinfoService myinfoService;

    // 회원 상세 정보를 가져오는 엔드포인트
    @PostMapping("/details")
    public ResponseEntity<?> getMemberDetails(@RequestBody MemberDTO request) {
        MemberDTO memberDTO = myinfoService.getMemberDetails(request.getMemberId());
        if (memberDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보 조회 실패");
        }
        return ResponseEntity.ok(memberDTO);
    }

    // 회원 정보를 업데이트하는 엔드포인트
    @PostMapping("/update")
    public ResponseEntity<?> updateMemberInfo(@RequestBody MemberDTO request) {
        boolean updated = myinfoService.updateMemberInfo(request);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 수정 실패");
        }
    }
}
