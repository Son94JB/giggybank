package com.d208.giggyrank.service;

import com.d208.giggyrank.dto.GameRankDto;
import com.d208.giggyrank.repository.HallOfFameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HallOfFameService {

    private final HallOfFameRepository hallOfFameRepository;

    // 게임 최고 점수(1위) 저장
    public ResponseEntity<String> saveLog(GameRankDto gameRankDto) {  // 받는 정보 : 유저 아이디, 점수
        return ResponseEntity.ok("good");
    }
}
