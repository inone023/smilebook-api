package com.example.smile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserListDTO {
    @JsonProperty("memberId")
    private String memberId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("memberStatus")
    private String memberStatus;
    public UserListDTO(String memberId, String nickname, String memberStatus) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.memberStatus = memberStatus;
    }
}
