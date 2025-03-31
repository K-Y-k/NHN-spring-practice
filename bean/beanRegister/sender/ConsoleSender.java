package org.example.sender;

public class ConsoleSender implements Sender {
    @Override
    public void sendMessage(Request request) {
        System.out.println("console sender. name: " + request.getName() + ", message: " + request.getMessage());
    }
}
