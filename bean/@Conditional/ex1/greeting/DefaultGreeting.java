package org.example.greeting;


public class DefaultGreeting implements Greeting {

    @Override
    public void sayHello() {
        System.out.println("DefaultGreeting: 기본 안녕");
    }
}
