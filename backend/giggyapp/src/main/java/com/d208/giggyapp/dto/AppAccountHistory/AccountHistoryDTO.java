package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountHistoryDTO {
    private Long id;
    private int amount;
    private String content;
    private Long transactionDate;
    private String transactionType;
    private String category;
    private int deposit;
    private int withdraw;
}
