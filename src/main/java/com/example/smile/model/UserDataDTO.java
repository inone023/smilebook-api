package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserDataDTO {
    private String userId;
    private String RFIDCardId;
    private String memberStatus;
    private Integer warningCount;

    public UserDataDTO(String userId, String RFIDCardId, String memberStatus, Integer warningCount) {
        this.userId = userId;
        this.RFIDCardId = RFIDCardId;
        this.memberStatus = memberStatus;
        this.warningCount = warningCount;
    }
}
