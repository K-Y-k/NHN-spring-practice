package org.example.customcode.greeting;

import org.example.framework.annotations.Part;

@Part
public class KoreanGreeting implements Greeting {

    public void sayHello() {
        System.out.println("안녕 세상");
    }
}
