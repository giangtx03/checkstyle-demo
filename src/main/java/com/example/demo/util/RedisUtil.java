package com.example.demo.util;

import com.example.demo.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisUtil implements CommandLineRunner {

    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        redisService.set("name", "ggiang2");
        redisService.set("name2", "ggiang12", 5000L);

        Thread.sleep(6000);

        System.out.println(redisService.get("name2"));
    }
}
