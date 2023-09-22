package com.d208.giggyrank.service;

import com.d208.giggyrank.dto.BeggerRankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HallOfBeggerService {
    private static final String RANK_KEY = "beggerRank";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional
    public void update(BeggerRankDto beggerRankDto){
        Integer targetAmount = beggerRankDto.getTargetAmount();
        Integer currentAmount = beggerRankDto.getCurrentAmount();
        Double consumptionRatio = (double) (currentAmount/targetAmount);
        redisTemplate.opsForZSet().add(RANK_KEY, beggerRankDto.getUserName(), consumptionRatio);
    }
}
