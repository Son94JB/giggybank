package com.d208.giggyapp.dto.begger;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BeggerNeighborDto {
    private UUID userId;
    private Long rank;
    private double ratio;
}
