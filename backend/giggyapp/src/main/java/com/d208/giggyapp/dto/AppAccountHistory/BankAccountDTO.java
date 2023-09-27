package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class BankAccountDTO {
    private String accountNumber;
    private UUID userId;
}
