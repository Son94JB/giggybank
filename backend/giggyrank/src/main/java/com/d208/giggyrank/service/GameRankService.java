package com.d208.giggyrank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRankService {


    // Redis 서버에 각 유저의 점수를 저장하는 로직
    // 점수를 불러올 때 해당 유저의 현재 저장된 있는 점수와 비교해서 새로운 점수가 더 크다면 바꿔서 저장
    // 아니라면 그냥 스킵

    }
}
