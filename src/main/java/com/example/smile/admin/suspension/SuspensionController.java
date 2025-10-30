package com.example.smile.admin.suspension;

import com.example.smile.model.SuspensionReasonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//회원 정지 기능을 처리하는 컨트롤러 클래스
public class SuspensionController {
    private final SuspensionService suspensionService;

    @Autowired
    public SuspensionController(SuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    //정지 사유 업데이트를 위한 post 요청 핸들러
    @PostMapping("/member/updateSuspensionReason")
    public ResponseEntity<?> updateSuspensionReason(@RequestBody SuspensionReasonDTO suspensionReasonDTO) {
        return suspensionService.updateSuspensionReason(suspensionReasonDTO);
    }

}
