package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AppAccountHistoryDto {
    private Long id;
    private int amount;
    private String content;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String category;
    private int deposit;
    private int withdraw;
}
