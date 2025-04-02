package org.example.sender;

import java.io.IOException;

public interface Sender {
    void sendMessage(Request request) throws IOException;
}
