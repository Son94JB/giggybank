package com.d208.giggyapp.dto.begger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeggerRankDto {
    private String userName;
    private double ratio;
}
