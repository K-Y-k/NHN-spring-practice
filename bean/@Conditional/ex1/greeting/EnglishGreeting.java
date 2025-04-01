package org.example.greeting;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnglishGreeting implements Greeting {

    @Override
    public void sayHello() {
        System.out.println("EnglishGreeting: hello world");
    }
}

