package com.d208.giggyrank.controller;

import com.d208.giggyrank.dto.GameRankDto;
import com.d208.giggyrank.service.GameRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class GameRankController {
    private final GameRankService gameRankService;

    // 게임 랭킹 저장
    @PostMapping("/game/new-score")
    public ResponseEntity<String> saveGameLog(@RequestBody GameRankDto gameRankDto) {
        return gameRankService.saveScore(gameRankDto);
    }
}
