package com.example.smile.admin.checkAdminCode;

import com.example.smile.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByAdminCode(String adminCode);
}