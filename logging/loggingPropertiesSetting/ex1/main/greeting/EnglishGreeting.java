package org.example.greeting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnglishGreeting implements Greeting {

    @Override
    public void sayHello() {

        Integer intValue = 1;
        String stringValue = "sando";

        //TODO-1 `{}` 플레이스 홀더를 이용해서, 변수를 넣을 수 있다.
        log.info("hello world - {} - {}", intValue, stringValue);

        try {
            throw new RuntimeException("This is expected exception");
        } catch (RuntimeException e) {
            //TODO-2 예외객체를 마지막 인자로 넣으면, 스택트레이스를 로그에 추가해준다.
            log.info("Lets log exception.", e);
        }

    }
}
