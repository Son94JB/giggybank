package com.d208.giggyapp.dto.begger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeggerNeighborResultDto {
    private String nickName;
    private Long rank;
    private double ratio;
}
