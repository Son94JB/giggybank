package com.d208.giggyrank.dto;


import lombok.Builder;
import lombok.Getter;


import java.util.UUID;

@Getter
@Builder
public class GameRankDto {
    private UUID userId;
    private int score;

}
