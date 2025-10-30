package com.example.smile.admin.userList;

import com.example.smile.model.UserListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
// 회원 목록 조회 기능을 처리하는 컨트롤러 클래스
public class UserListController {
    @Autowired
    private UserListService userListService;

    // 모든 회원 목록 조회 메서드
    @GetMapping("/users")
    public List<UserListDTO> getAllUsers() {
        return userListService.getAllUsers();
    }
}
