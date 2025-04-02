package org.example.greeting;

import org.example.annotation.CustomAnnotation;

public class EnglishGreeting implements Greeting {

    /**
     * 사용자 어노테이션을 붙임
     */
    @CustomAnnotation
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
