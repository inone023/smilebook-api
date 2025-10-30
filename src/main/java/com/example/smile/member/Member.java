package com.example.smile.member;

import com.example.smile.admin.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.smile.book.Book;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
// 회원 앤티티 클래스
public class Member implements UserDetails {

    @Id
    @Column(name = "memberId", length = 45, nullable = false)
    private String memberId;

    @Column(name = "nickname", length = 45, nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "phoneNumber", length = 20, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "RFIDcardId", length = 50)
    private String memberRfid;

    //경고, 정지 관련 속성들
    @Column(name = "memberStatus", length = 20)
    private String memberStatus = "이용 가능한 사용자";
    @Column(name = "warningCount")
    private Integer warningCount = 0;
    @Column(name = "warningReason", length = 150)
    private String warningReason;
    @Column(name = "suspensionReason", length = 150)
    private String suspensionReason;

    //push 알림 토큰
    @Column(name = "fcmToken", length = 255)
    private String fcmToken;


    @Column(name="role")
    private String role = "ROLE_USER"; // Spring Security 규약에 따라 "ROLE_" prefix를 사용

    @OneToOne
    @JoinColumn(name = "adminId", referencedColumnName = "adminId")
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @JsonIgnore // JSON 직렬화 시 무한 루프 방지
    @ManyToMany
    @JoinTable(
            name = "member_book",
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "bookId"))
    private Set<Book> borrowedBooks;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
