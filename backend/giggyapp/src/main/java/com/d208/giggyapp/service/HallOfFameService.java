package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.game.HallOfFame;
import com.d208.giggyapp.dto.game.GameRankDto;
import com.d208.giggyapp.repository.HallOfFameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class HallOfFameService {

    private final HallOfFameRepository hallOfFameRepository;

    // 게임 최고 점수(1위) 저장
    @Transactional
    public ResponseEntity<String> saveLog(GameRankDto gameRankDto) {  // 받는 정보 : 유저 아이디, 점수
        // 라운드를 계산하기 위해서 출시일과 오늘의 차이를 계산한다.
        Long today = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        LocalDateTime releaseDate = LocalDateTime.of(2023, 9, 20, 0, 0);
        Long releaseDateL = releaseDate.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        Long differenceL = today - releaseDateL;

        // 7 * 24 * 60 * 60 * 1000 = 604800000 7일을 밀리초로 바꾼 값
        double roundD = (double) differenceL/604800000;
        // 나눈 뒤 소수점에서 올림하고 int로 바꿔준다
        int round = (int) Math.ceil(roundD);

        HallOfFame hallOfFame = HallOfFame.builder()
                .score(gameRankDto.getScore())
                .userId(gameRankDto.getUserId())
                .round(round)
                .build();

        hallOfFameRepository.save(hallOfFame);

        return ResponseEntity.ok("saved");
    }
}
