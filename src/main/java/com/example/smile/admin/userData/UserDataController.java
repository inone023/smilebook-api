package com.example.smile.admin.userData;

import com.example.smile.model.UserDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
//회원 상태 조회 기능을 처리하는 컨트롤러 클래스
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    //회원 정보 조회 메서드
    @GetMapping("/{memberId}")
    public UserDataDTO getMemberInfo(@PathVariable String memberId) {
        return userDataService.getMemberInfo(memberId);
    }
}
