package com.d208.giggyrank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HallOfBeggerController {
    @Autowired
    public final RedisTemplate<String, String> redisTemplate;

    
}
