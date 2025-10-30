package com.example.smile.admin.userList;

import com.example.smile.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<Member, String> {
}
