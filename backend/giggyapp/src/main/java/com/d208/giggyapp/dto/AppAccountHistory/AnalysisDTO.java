package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnalysisDTO {
    private String categoryName;
    private Integer price;
}
