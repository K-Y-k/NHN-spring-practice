package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.sender.Request;
import org.example.sender.Sender;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final Sender sender;

    @Override
    public void run(ApplicationArguments args) throws IOException {

        Request request = new Request("sando", "hello");
        sender.sendMessage(request);

    }
}