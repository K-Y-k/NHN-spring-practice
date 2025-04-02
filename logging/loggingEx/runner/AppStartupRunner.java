package org.example.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.student.HighSchoolStudent;
import org.example.student.UniversityStudent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * - 롬복에서는
 *   private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EnglishGreeting.class); 를 만들어준다.
 * - @Slf4j 만 붙여주면 log.info(), log.debug(), log.warn() 등으로 활용할 수 있다.
 */
@RequiredArgsConstructor
@Component
//TODO-1 logback 설정을 위한 코드가 필요하다 ** hint lombok 사용 여부에 따라 설정이 다르다.**
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final HighSchoolStudent highSchoolStudent;
    private final UniversityStudent universityStudent;

    @Override
    public void run(ApplicationArguments args) {

        //TODO-2 System.out.println 을 다른 방식으로 바꾼다.
        System.out.println(highSchoolStudent.getSchool());
        System.out.println(highSchoolStudent.echo("hello, high school"));

        /**
         * 로그 레벨 종류
         */
        // 중요도 1 - TRACE : 가장 상세한 정보를 제공. 디버깅 목적으로 사용되며,
        //                   애플리케이션의 내부 상태나 변수의 변화, 함수 호출과 같은 매우 상세한 운영 정보를 로깅
        log.trace("trace");

        // 중요도 2 - DEBUG : 개발 중에 일반적인 문제 해결을 위해 사용.
        //                   애플리케이션의 실행 흐름을 추적하거나 상태를 확인하는데 필요한 상세 정보를 제공
        log.debug("{}", highSchoolStudent.echo("hello, university"));

        // 중요도 3 - INFO : 일반적인 운영에 필요한 유용한 정보를 로그. 시스템의 동작 상태나 중요한 이벤트 발생을 알릴 때 사용되며, 정상적인 작동 조건에서 유용
        log.info(universityStudent.getSchool());

        // 중요도 4 - WARN : 잠재적인 문제를 예고하는 경고 메시지를 로그. 에러는 아니지만, 주의가 필요하거나 예상치 못한 상황이 발생했을 때 사용.
        //                  ex) 사용되지 않는 API의 사용, 예상치 않은 상황 등이 여기에 해당.
        log.warn("warn - {}", new Exception().getMessage());

        // 중요도 5- ERROR : 실제 오류나 문제가 발생했을 때 사용.
        //                  이 로그 레벨의 메시지는 주로 예외 처리와 연관되어 있으며, 시스템의 정상적인 동작에 영향을 미치는 중대한 이슈를 나타냄.
        log.error("error - {}", new Exception().getMessage());

        // 중요도 6- FATAL : 가장 심각한 오류를 나타냄. 이 로그 레벨의 이벤트는 애플리케이션의 실행을 중단시킬 수 있는 치명적인 문제를 의미.
        //                  ex) 시스템 컴포넌트의 실패나 데이터 손실 등이 이에 해당.
        // logback 은 FATAL를 지원하지 않는다. ERROR 로 같이 처리한다.
    }
}