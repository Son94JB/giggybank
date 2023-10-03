package com.d208.giggyrank.service;

import com.d208.giggyrank.dto.BeggerRankDto;
import com.d208.giggyrank.dto.BeggerRankNeighborDto;
import com.d208.giggyrank.dto.BeggerRankResultDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class HallOfBeggerService {
    private final ZSetOperations<String, String> zSetOperations;

    public HallOfBeggerService(RedisTemplate<String, String> redisTemplate) {
        this.zSetOperations = redisTemplate.opsForZSet();
    }
    private final String rankingKey = "begger_ranking";

    public long updateScore(BeggerRankDto beggerRankDto){
        double ratio = (double) beggerRankDto.getCurrentAmount() /beggerRankDto.getTargetAmount();
        String userId = beggerRankDto.getUserId().toString();
        zSetOperations.add(rankingKey, userId, ratio);

        // Redis ZSet의 rank는 0부터 시작하며, 작은 점수가 0번째입니다.
        // 따라서 큰 점수일수록 rank 값이 크고, 그러므로 순위는 작아집니다.
        // 순위를 얻기 위해선 전체 사이즈에서 rank 값을 빼주고 1을 더해야 합니다.
        Long rank = zSetOperations.reverseRank(rankingKey, beggerRankDto.getUserId().toString());

        if (rank == null) {
            throw new RuntimeException("Failed to get the rank of user: " + beggerRankDto.getUserId());
        }

        return (zSetOperations.size(rankingKey) - rank);
    }


    public List<BeggerRankResultDto> getTopRanking(){
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = zSetOperations.rangeWithScores(rankingKey, 0, 9);

        return rangeWithScores.stream()
                .map(tuple -> new BeggerRankResultDto(UUID.fromString(tuple.getValue()), tuple.getScore()))
                .collect(Collectors.toList());
    }

    public List<BeggerRankNeighborDto> getUserRankAndNeighbors(BeggerRankDto beggerRankDto) {
        Long rank = zSetOperations.reverseRank(rankingKey, beggerRankDto.getUserId().toString());

        if (rank == null) {
            throw new RuntimeException("Failed to get the rank of user: " + beggerRankDto.getUserId());
        }

        // 유저 랭킹에서 위로 2명, 아래로 2명을 포함한 범위 계산
        Long start = Math.max(0, rank - 2); // 시작 인덱스가 음수가 되지 않도록 보장
        Long end = rank + 2;

        // 해당 범위의 유저 ID와 점수(score)를 가져옴
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores =
                zSetOperations.reverseRangeWithScores(rankingKey, start, end);

        // 결과 변환: User ID와 Rank, Score 정보 포함
        List<BeggerRankNeighborDto> result = new ArrayList<>();

        for (ZSetOperations.TypedTuple<String> tuple : rangeWithScores) {
            result.add(BeggerRankNeighborDto.builder()
                    .userId(UUID.fromString(tuple.getValue()))
                    .rank(++start)
                    .ratio(tuple.getScore())
                    .build());
        }

        return result;
    }

}
