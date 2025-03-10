package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object val, long time){
        redisTemplate.opsForValue().set(key, val, time, TimeUnit.MILLISECONDS);
    }

    public void set(String key, Object val){
        redisTemplate.opsForValue().set(key, val);
    }

    public Object get(String key){
        return  redisTemplate.opsForValue().get(key);
    }
}
