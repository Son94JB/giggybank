package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.game.HallOfFame;
import com.d208.giggyapp.dto.game.GameRankDto;
import com.d208.giggyapp.repository.HallOfFameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class GameRankService {

    private final HallOfFameRepository hallOfFameRepository;

    // 게임 최고 점수(1위) 명예의 전당에 저장
    @Transactional
    public ResponseEntity<String> saveLog(GameRankDto gameRankDto) {  // 받는 정보 : 유저 아이디, 점수

        HallOfFame hallOfFame = HallOfFame.builder()
                .score(gameRankDto.getScore())
                .userId(gameRankDto.getUserId())
                .round(gameRankDto.getRound())
                .build();

        hallOfFameRepository.save(hallOfFame);

        return ResponseEntity.ok("명예의 전당 등록 완료");
    }
}
