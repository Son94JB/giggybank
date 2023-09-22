package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppAccountHistoryDto {
    private String status;
    private List<DataBody> data;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class DataBody {
        private Long id;
        private int amount;
        private String content;
        private LocalDateTime transactionDate;
        private String transactionType;
        private String category;
        private int deposit;
        private int withdraw;
    }
}
