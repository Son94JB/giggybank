package com.d208.giggyrank.domain.game;


import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("GameRank")
public class GameRank {

    @Id
    private Long id;

    private int round;
    private int score;

    private UUID userId;

    public void newScore(int score) {
        this.score = score;
    }
}
