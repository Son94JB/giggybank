package com.d208.giggyrank.domain.game;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallOfFame {

    @Id @GeneratedValue
    private Long id;

    private int round;
    private int score;

    private UUID userId;

}
