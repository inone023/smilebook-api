package com.example.smile.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="admin")
//admin 테이블 생성
public class Admin {
    @Id
    @Column(name = "adminId", length = 45, nullable = false)
    private String adminId;

    @Column(name = "adminCode", length = 60, nullable = false, unique = true)
    private String adminCode;
}
