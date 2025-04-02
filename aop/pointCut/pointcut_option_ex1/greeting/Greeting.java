package org.example.greeting;


import org.example.annotation.AtGreeting;
import org.example.annotation.MethodAtInterface;

@AtGreeting
public interface Greeting {

    @MethodAtInterface
    void sayHello();
}
