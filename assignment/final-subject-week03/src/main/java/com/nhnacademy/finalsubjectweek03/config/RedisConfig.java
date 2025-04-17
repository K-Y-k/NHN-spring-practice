package com.nhnacademy.finalsubjectweek03.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * 만약 @class 부분을 지웠다면 어떤 클래스 객체인지 redis가 판별하지 못하므로
     * ObjectMapper 활용해서 명시한 클래스 객체 변환에 사용 (ex) objectMapper.convertValue(o, Member.class));
     */
    @Bean
    public ObjectMapper redisObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper redisObjectMapper) {
        RedisTemplate<String, Object> sessionRedisTemplate = new RedisTemplate<>();
        sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);
        sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper));

        /// GenericJackson2JsonRedisSerializer은 Redis에서 데이터를 JSON 형식으로 직렬화/역직렬화할 때 사용
        /**
         * Redis에 JSON 형식으로 데이터를 넣을 때 @class 부분을 제거하기 위해 ObjectMapper를 사용
         * 객체를 판별하기 위해 GenericJackson2JsonRedisSerializer에 ObjectMapper를 인자로 전달해줌
         */
        sessionRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper));

        return sessionRedisTemplate;
    }
}
