package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class SuspensionReasonDTO {
    private String memberId;
    private String suspensionReason;

    public SuspensionReasonDTO(String memberId, String suspensionReason) {
        this.memberId = memberId;
        this.suspensionReason = suspensionReason;
    }
}
