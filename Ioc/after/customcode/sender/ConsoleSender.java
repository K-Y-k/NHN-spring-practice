package org.example.customcode.sender;

import org.example.framework.annotations.Part;



public class ConsoleSender implements Sender {

    public void sendMessage(Request request) {
        System.out.println("console sender. name: " + request.getName() + ", message: " + request.getMessage());
    }
}
