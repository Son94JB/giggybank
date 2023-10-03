package com.d208.giggyrank.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Data
@Getter
@Setter
public class BeggerRankNeighborDto {
    private UUID userId;
    private Long rank;
    private double ratio;
}
