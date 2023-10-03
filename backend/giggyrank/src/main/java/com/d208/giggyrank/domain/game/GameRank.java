package com.d208.giggyrank.domain.game;


import lombok.*;
import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRank {

    private Long id;

    private int round;
    private int score;

    private UUID userId;

}
