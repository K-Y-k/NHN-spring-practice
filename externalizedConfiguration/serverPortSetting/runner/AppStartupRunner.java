package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    /**
     * 웹을 띄울 때 웹 서버들은 ServerProperties 클래스의 port 필드를 참조한다.
     *
     * TomcatServletWebServerFactory에서 내부적으로 port 값이 없다면
     * 8080 으로 띄우도록 설정 되어있다.
     */
    //TODO-1 ServerProperties 값을 보기 위해서 빈처럼 가져와봤다.
    private final ServerProperties serverProperties;


    @Override
    public void run(ApplicationArguments args) {

        System.out.println(serverProperties.getPort());

    }
}