package com.example.smile.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class WarningDTO {
    private String memberId;
    private String warningReason;
}
