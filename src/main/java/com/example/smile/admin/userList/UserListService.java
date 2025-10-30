package com.example.smile.admin.userList;

import com.example.smile.admin.userData.UserDataRepository;
import com.example.smile.member.Member;
import com.example.smile.model.UserListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
//회원 목록 처리 서비스 클래스
public class UserListService {

    @Autowired
    private UserDataRepository userDataRepository;

    // 모든 회원 목록 조회 메서드
    public List<UserListDTO> getAllUsers() {
        List<Member> members = userDataRepository.findAll();
        List<UserListDTO> userListDTOs = new ArrayList<>();

        for (Member member : members) {
            UserListDTO userListDTO = new UserListDTO(
                    member.getMemberId(),
                    member.getNickname(),
                    member.getMemberStatus()
            );
            userListDTOs.add(userListDTO);
        }

        return userListDTOs;
    }
}
