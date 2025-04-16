package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FailCounter {
    private final Map<String, Integer> failMap = new ConcurrentHashMap<>();

    public void increment(String userName) {
        failMap.merge(userName, 1, Integer::sum);
    }

    public Integer getCounter(String userName) {
        return failMap.getOrDefault(userName, 0);
    }

    public void reset(String userName) {
        failMap.remove(userName);
    }
}
