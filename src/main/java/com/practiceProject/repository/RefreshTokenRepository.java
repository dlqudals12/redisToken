package com.practiceProject.repository;


import com.practiceProject.exception.RefreshException;
import com.practiceProject.redis.RefreshToken;
import com.practiceProject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate redisTemplate;



    public void save(RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set( refreshToken.getUserIdx().toString(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getRefreshToken(), 60 * 60 * 24 , TimeUnit.SECONDS);
    }

    public Boolean findById(String idx) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(idx);

        System.out.println(refreshToken);

        boolean b = JwtTokenProvider.validateToken(refreshToken);


        return Objects.isNull(refreshToken) || !b;
    }
}
