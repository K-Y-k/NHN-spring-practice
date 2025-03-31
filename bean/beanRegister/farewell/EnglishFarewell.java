package org.example.farewell;

import org.springframework.stereotype.Component;

/**
 * 구현체가 달라도 부모 인터페이스로 반환한 Farewell이 2개가 되면
 * 어떤 구현체로 사용할지 프레임워크가 알지 못한다.
 * (동일 객체 2개 이상일 때는 @Primary로 우선순위 부여해야함)
 */
//@Component
public class EnglishFarewell implements Farewell {

    @Override
    public void sayGoodBye() {
        System.out.println("good bye");
    }
}
