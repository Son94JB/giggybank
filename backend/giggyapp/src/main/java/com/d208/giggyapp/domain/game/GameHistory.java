package com.d208.giggyapp.domain.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameHistory {

    @Id @GeneratedValue
    private Long id;

    private int round;
    private int score;

    @JoinColumn(name = "USER_ID")
    private UUID userId;
}
