package org.example.framework.strategy;

public interface Steps {
    void initialize() throws Exception;

    void process();

    void destroy();
}
