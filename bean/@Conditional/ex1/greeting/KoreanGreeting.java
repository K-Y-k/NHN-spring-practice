package org.example.greeting;


public class KoreanGreeting implements Greeting {

    @Override
    public void sayHello() {
        System.out.println("KoreanGreeting: 안녕 세상");
    }

}

