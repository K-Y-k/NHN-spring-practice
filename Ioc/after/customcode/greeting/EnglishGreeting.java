package org.example.customcode.greeting;

import org.example.framework.annotations.Part;

@Part
public class EnglishGreeting implements Greeting {

    public void sayHello() {
        System.out.println("hello world");
    }
}
