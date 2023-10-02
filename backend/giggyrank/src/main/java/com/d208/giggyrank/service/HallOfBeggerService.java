package com.d208.giggyrank.service;

import com.d208.giggyrank.dto.BeggerRankDto;
import com.d208.giggyrank.dto.BeggerRankResultDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class HallOfBeggerService {
    private final ZSetOperations<String, String> zSetOperations;

    public HallOfBeggerService(RedisTemplate<String, String> redisTemplate) {
        this.zSetOperations = redisTemplate.opsForZSet();
    }
    private final String rankingKey = "begger_ranking";

    public void updateScore(BeggerRankDto beggerRankDto){
        double ratio = (double) beggerRankDto.getCurrentAmount() /beggerRankDto.getTargetAmount();
        zSetOperations.add(rankingKey, beggerRankDto.getUserName(), ratio);
    }

    public List<BeggerRankResultDto> getTopRanking(){
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = zSetOperations.reverseRangeWithScores(rankingKey, 0, 9);

        return rangeWithScores.stream()
                .map(tuple -> new BeggerRankResultDto(tuple.getValue(), tuple.getScore()))
                .collect(Collectors.toList());
    }

    public Long getUserRank(String username){
        Long rank = zSetOperations.reverseRank(rankingKey, username);
        if (rank != null){
            return rank +1;
        }else{
            return null;
        }
    }
}
