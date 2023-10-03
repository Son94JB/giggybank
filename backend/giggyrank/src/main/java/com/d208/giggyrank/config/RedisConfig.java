package com.d208.giggyrank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Bean
    public RedisConnectionFactory beggerConnectionFactory(){

        return new LettuceConnectionFactory(host, 6380);
    }

    @Bean
    RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key 깨짐 방지
        redisTemplate.setValueSerializer(new StringRedisSerializer());//value 깨짐 방지
        redisTemplate.setConnectionFactory(beggerConnectionFactory());
        return redisTemplate;
    }
}
