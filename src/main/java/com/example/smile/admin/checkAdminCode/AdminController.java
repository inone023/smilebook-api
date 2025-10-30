package com.example.smile.admin.checkAdminCode;

import com.example.smile.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//관리자 모드 전환: 관리자 코드를 확인하는 기능
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/checkAdminCode")
    public ResponseEntity<String> checkAdminCode(@RequestParam String adminCode) {
        Admin admin = adminRepository.findByAdminCode(adminCode);
        if (admin != null) {
            return ResponseEntity.ok("코드 일치");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("코드 불일치");
        }
    }
}
