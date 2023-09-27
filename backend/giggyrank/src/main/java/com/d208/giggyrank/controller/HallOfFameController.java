package com.d208.giggyrank.controller;

import com.d208.giggyrank.dto.GameRankDto;
import com.d208.giggyrank.service.GameRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class HallOfFameController {
    private final GameRankService gameRankService;

    // 게임 로그 저장
    @PostMapping("/game/log")
    public ResponseEntity<String> saveGameLog(GameRankDto gameRankDto) {
        // 모바일에서 유저 아이디, 게임 점수를 받는다
//        return gameRankService.saveLog(gameRankDto);
        return ResponseEntity.ok("ok");
    }
}
