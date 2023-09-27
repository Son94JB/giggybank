package com.d208.giggyapp.controller;

import com.d208.giggyapp.dto.game.GameRankDto;
import com.d208.giggyapp.service.HallOfFameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class HallOfFameController {
    private final HallOfFameService hallOfFameService;

    // 게임 로그 저장
    @PostMapping("/game/log")
    public ResponseEntity<String> saveGameLog(GameRankDto gameRankDto) {
        // 모바일에서 유저 아이디, 게임 점수를 받는다
        return hallOfFameService.saveLog(gameRankDto);
    }
}
