package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.sender.MessageSender;
import org.example.sender.Request;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final MessageSender messageSender;

    @Override
    public void run(ApplicationArguments args) {

        // TODO-6 원하는 메시지를 설정한다.
        messageSender.sendMessage(new Request("111", "1111111"));

    }
}