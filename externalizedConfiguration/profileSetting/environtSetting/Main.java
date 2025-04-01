package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
public class Main {

    /**
     * 환경마다 다른 설정 파일을 참조하도록 할 수 있고 Profile 이라 부른다.
     *
     * 구성편집에서 환경변수로 설정
     * ex) active profile탭에는 application-dev.properties를 적용하고 싶으면 dev만 넣음
     * ex) environment탭에는 application-dev.properties를 적용하고 싶으면 spring.profiles.active=dev 넣음
     */
    //TODO-2 실행 할 때, profile 정보를 넣어서 실행한다.
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}