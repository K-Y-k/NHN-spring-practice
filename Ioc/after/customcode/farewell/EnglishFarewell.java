package org.example.customcode.farewell;

import org.example.framework.annotations.First;
import org.example.framework.annotations.Part;

/**
 * 초기화를 원하는 클래스는 @Part를 붙여주면 프레임워크가 객체를 생성해준다. (기본 생성자로 초기화)
 *
 * @Part 가 여러 클래스에 붙어있다면 @First 가 붙은 클래스를 우선시 한다.
 *
 * @First 사용자 어노테이션을 정의했고 리플렉션으로 클래스를 스캔할 때 두 어노테이션이 있으면
 * @First가 붙은 클래스로 반환하도록 구현해 놓았기 때문
 */
@Part
@First
public class EnglishFarewell implements Farewell {

    public void sayGoodBye() {
        System.out.println("good bye");
    }
}
