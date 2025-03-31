package org.example.runner;

import org.example.greeting.Greeting;
import org.example.greeting.JapaneseGreeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final Greeting greeting;
    private final Greeting koreanGreeting;
    private final Greeting japaneseGreeting;
    private final Greeting qKoreanGreeting;
    private final Greeting myCustomBeanName;
    private final Greeting qMyCustomBeanName;

    private final KoreanGreeting cKoreanGreeting;
    private final JapaneseGreeting cJapaneseGreeting;



    public AppStartupRunner(Greeting greeting,
                            Greeting koreanGreeting,
                            Greeting japaneseGreeting,
                            Greeting myCustomBeanName,
                            /**
                             * 자동 주입 시 특정 빈을 명시적으로 지정할 수 있게 해주는 어노테이션이다.
                             * 이름으로 빈을 구분하여 주입할 때 사용된다.
                             */
                            //TODO-2 @Bean 에서 이름을 지정하지 않으면 자동으로 적절한 이름을 선택한다(메소드명)
                            @Qualifier("koreanGreeting") Greeting qKoreanGreeting,
                            @Qualifier("myCustomBeanName") Greeting qMyCustomBeanName,
                            //TODO-3 이름을 지정하는 경우 자동으로 이름을 지정하지 않는다.
                            //@Qualifier("japaneseGreeting") Greeting qJapaneseGreeting,
                            KoreanGreeting cKoreanGreeting,
                            JapaneseGreeting cJapaneseGreeting) {
        this.greeting = greeting;
        this.koreanGreeting = koreanGreeting;
        this.japaneseGreeting = japaneseGreeting;
        this.myCustomBeanName = myCustomBeanName;
        this.qKoreanGreeting = qKoreanGreeting;
        this.qMyCustomBeanName = qMyCustomBeanName;
        this.cKoreanGreeting = cKoreanGreeting;
        this.cJapaneseGreeting = cJapaneseGreeting;
    }

    @Override
    public void run(ApplicationArguments args) {
        //TODO-4 특별한 설정을 하지 않은 경우 primary 로 주입이 된다.
        greeting.sayHello();
        koreanGreeting.sayHello();
        japaneseGreeting.sayHello();
        myCustomBeanName.sayHello();

        System.out.println("====================");

        //TODO-5 @Qualifier 를 지정할 경우 적절한 빈이 주입 된다.
        qKoreanGreeting.sayHello();
        qMyCustomBeanName.sayHello();

        System.out.println("====================");


        //TODO-6 인터페이스가 아닌 구현체를 바로 주입하는 경우 사용할 수 있다 (권장 x)
        cKoreanGreeting.sayHello();
        cJapaneseGreeting.sayHello();

    }
}