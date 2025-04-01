package org.example.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {


    // hello world+안녕 세상
    @Value("${greeting.english}+${greeting.korean}")
    private String value1;

    // hello world안녕 세상
    @Value("${greeting.english}" + "${greeting.korean}")
    private String value2;

    // 12
    @Value("${number.one}${number.two}")
    private String value3;

    // 12
    @Value("${number.one}" + "${number.two}")
    private String value4;

    // 12
    @Value("${number.one}" + "${number.two}")
    private int value5;

    // 3
    @Value("#{1+2}")
    private String value6;

    // 3
    @Value("#{${number.one} + ${number.two}}")
    private int value7;

    // true
    @Value("#{${number.one} eq 1}")
    private boolean value8;

    // false
    @Value("#{${number.one} eq \"1\"}")
    private boolean value9;

    // 7
    @Value("#{${number.one} + ${number.two} + (${number.three} ?: 4)}")
    private int value10;


    //TODO-2 하나씩 찍어가면서 예상하는 답이 나오는지 확인한다.
    @Override
    public void run(ApplicationArguments args) {
        System.out.println(value10);
    }
}