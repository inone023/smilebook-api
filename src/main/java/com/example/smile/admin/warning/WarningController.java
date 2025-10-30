package com.example.smile.admin.warning;

import com.example.smile.model.SuspensionReasonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//회원 경고 기능을 처리하는 컨트롤러 클래스
public class WarningController {
    @Autowired
    private WarningService warningService;

    // 회원의 경고 정보를 업데이트하는 메서드
    @PostMapping("/member/updateWarning")
    public ResponseEntity<Void> updateWarning(@RequestBody SuspensionReasonDTO suspensionReasonDTO) {
        try {
            warningService.updateWarning(suspensionReasonDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}