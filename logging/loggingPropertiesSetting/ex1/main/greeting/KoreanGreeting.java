package org.example.greeting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KoreanGreeting implements Greeting {

    @Override
    public void sayHello() {
        log.info("안녕 세상");
    }

}

