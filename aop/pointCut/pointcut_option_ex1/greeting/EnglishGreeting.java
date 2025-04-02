package org.example.greeting;

import org.example.annotation.AtEnglishGreeting;

@AtEnglishGreeting
public class EnglishGreeting implements Greeting {


    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
