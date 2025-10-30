package com.example.smile.admin.userData;

import com.example.smile.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);
}
