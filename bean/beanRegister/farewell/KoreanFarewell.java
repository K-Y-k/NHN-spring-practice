package org.example.farewell;


import org.springframework.stereotype.Component;

//TODO-2 KoreanFarewell 을 Bean 으로 등록한다.
/**
 * 방식2: @Component로 등록 방식
 */
@Component
public class KoreanFarewell implements Farewell {

    @Override
    public void sayGoodBye() {
        System.out.println("good bye");
    }
}
