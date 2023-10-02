package com.d208.giggyrank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeggerRankDto {
    private String userName;
    private int targetAmount;
    private int currentAmount;
}
