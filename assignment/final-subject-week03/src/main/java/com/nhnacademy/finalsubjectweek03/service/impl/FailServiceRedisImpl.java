package com.nhnacademy.finalsubjectweek03.service.impl;

import com.nhnacademy.finalsubjectweek03.service.FailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
public class FailServiceRedisImpl implements FailService {

    private final StringRedisTemplate redisTemplate;
    private final String HASH_NAME = "LoginFail:";


    @Override
    public void saveFailedId(String memberId) {
        if (existsFailedId(memberId)) {
            return;
        }

        /// TTL(Time To Live) : Redis에서 키가 자동으로 삭제되기까지 남은 시간
        /// 5분 설정
        redisTemplate.opsForValue().set(HASH_NAME + memberId, memberId, 5, TimeUnit.MINUTES);
        log.info("Redis에 실패id 생성 : {}, {}", HASH_NAME + memberId, memberId);
    }

    @Override
    public void deleteFailedId(String sessionId) {
        redisTemplate.delete(sessionId);
    }

    @Override
    public boolean existsFailedId(String sessionId) {
        Boolean exists = redisTemplate.hasKey(HASH_NAME + sessionId);
        if (exists) {
            getTTLToSeconds(sessionId);
            return true;
        }

        return false;
    }

    @Override
    public Long getTTLToSeconds(String sessionId) {
        Long ttl = redisTemplate.getExpire(HASH_NAME + sessionId, TimeUnit.SECONDS);
        log.info("{} TTL:{}초", sessionId, ttl);

        return ttl;
    }
}
