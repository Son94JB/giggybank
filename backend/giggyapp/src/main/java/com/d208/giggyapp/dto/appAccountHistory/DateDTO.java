package com.d208.giggyapp.dto.appAccountHistory;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DateDTO {
    private UUID userId;
    private String startDate;
    private String endDate;
}
