package com.d208.giggyapp.dto.begger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeggerNeighborResultDto {
    private String nickName;
    private Long rank;
    private double ratio;
}
