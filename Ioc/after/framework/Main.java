package org.example.framework;

import org.example.framework.factory.Factory;
import org.example.framework.strategy.Steps;


public class Main {

    public static void main(String[] args) throws Exception {

        Steps steps = Factory.getInstance(Steps.class);

        if (steps == null) {
            throw new RuntimeException("Steps not initialized");
        }


        steps.initialize();
        steps.process();
        steps.destroy();
    }
}