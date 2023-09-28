package com.d208.giggyapp.dto.appAccountHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    private String accountNumber;
    private UUID userId;
}
