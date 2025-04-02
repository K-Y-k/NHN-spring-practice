package org.example.greeting;

public class EnglishGreeting implements Greeting {

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    //TODO-2 exception 을 발생 시키고 실행해본다.
    //    public void sayHello() {
    //        System.out.println("I will throw exception");
    //        throw new RuntimeException("I will throw exception");
    //    }
}
