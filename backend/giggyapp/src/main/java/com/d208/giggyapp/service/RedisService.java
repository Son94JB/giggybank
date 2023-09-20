package com.d208.giggyapp.service;

import com.d208.giggyapp.dto.User.TokenDto;
import com.d208.giggyapp.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisService {
    private final UserRedisRepository userRedisRepository;

    // 토큰을 레디스에 추가
    public void setAccessToken(TokenDto tokenDto) {
        userRedisRepository.save(tokenDto);
    }

    // 토큰이 레디스에 있는지 확인
    public boolean getAccessToken(String key) {
        if (userRedisRepository.findById(key).isEmpty()) return false;

        return true;
    }
}
