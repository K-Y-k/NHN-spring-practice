package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.farewell.Farewell;
import org.example.greeting.Greeting;
import org.example.sender.Sender;
import org.example.sender.Request;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {

        Greeting greeting = applicationContext.getBean(Greeting.class);
        greeting.sayHello();

        Sender sender = applicationContext.getBean(Sender.class);

        //TODO-5 이름과 원하는 메시지를 채운다.
        Request request = new Request("김용경", "빈 등록 실습 - 완료");
        sender.sendMessage(request);

        Farewell farewell = applicationContext.getBean(Farewell.class);
        farewell.sayGoodBye();
    }
}