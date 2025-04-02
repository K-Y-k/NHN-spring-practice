package org.example.greeting;

import org.example.annotation.AtAdvancedEnglishGreeting;
import org.example.annotation.Method;

@AtAdvancedEnglishGreeting
public class AdvancedEnglishGreeting extends EnglishGreeting implements SpecialGreeting {

    @Method
    @Override
    public void saySpecialHello(SpecialHelloTarget specialHelloTarget) {
        System.out.println("special thanks to " + specialHelloTarget.firstName + " " + specialHelloTarget.lastName);
    }

}