package com.d208.giggyrank.service;

import com.d208.giggyrank.domain.game.GameRank;
import com.d208.giggyrank.dto.GameRankDto;
import com.d208.giggyrank.repository.GameRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameRankService {

    private final GameRankRepository gameRankRepository;

    // Redis 서버에 각 유저의 점수를 저장하는 로직
    // 점수를 불러올 때 해당 유저의 현재 저장된 있는 점수와 비교해서 새로운 점수가 더 크다면 바꿔서 저장
    // 아니라면 그냥 스킵
    public Boolean saveScore(GameRankDto gameRankDto) {

        UUID userId = gameRankDto.getUserId();
        int newScore = gameRankDto.getScore();

        // 일단 라운드 계산을 해준다
        Long today = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        LocalDateTime releaseDate = LocalDateTime.of(2023, 9, 20, 0, 0);
        Long releaseDateL = releaseDate.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        Long differenceL = today - releaseDateL;

        // 7 * 24 * 60 * 60 * 1000 = 604800000 7일을 밀리초로 바꾼 값
        double roundD = (double) differenceL/604800000;
        // 나눈 뒤 소수점에서 올림하고 int로 바꿔준다
        int round = (int) Math.ceil(roundD);

        // 게임 랭킹에 기존 랭킹이 있는지 확인
        // 없으면 바로 저장해주고 있으면 비교해서 높은 점수를 저장
        Optional<GameRank> optionalGameRank = gameRankRepository.findById(userId);
        if (!optionalGameRank.isPresent()) {
            GameRank newGameRank = new GameRank();
            newGameRank.setUserId(userId);
            newGameRank.setRound(round);
            newGameRank.setScore(newScore);
            gameRankRepository.save(newGameRank);
            return true;
        } else {
            GameRank existingGameRank = optionalGameRank.get();
            if (existingGameRank.getScore() < newScore) {
                existingGameRank.newScore(newScore);
                gameRankRepository.save(existingGameRank);
                return true;
            } else {
                return false;
            }
        }
    }
}
