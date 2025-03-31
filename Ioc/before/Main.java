package org.example;

import org.example.strategy.Communication;
import org.example.strategy.Steps;

public class Main {

    /**
     * 현재 프로그램의 문제점
     * 1.요구사항에 따라 주입해야하는 클래스 변경에서의 문제
     * 2.로직 순서가 우리가 지정하는데 실수로 순서가 바꿀 수 있다.
     * 3.초기화 시점을 직접 코드를 확인하지 않는 이상은 모른다. (new KoreanGreeting()의 위치)
     */

    // TODO-1 프로그램의 제어을 사용자가 직접하고 있어서 initialize() -> process() -> destroy() 순서로 동작할 것이라 장담할 수 없다.
    //  사용자 실수로 destroy() -> process() -> initialize() 순으로 호출할 수도 있음
    public static void main(String[] args) throws Exception {
        Steps steps = new Communication();

        // 로직 순서가 우리가 지정하는데 실수로 순서가 바꿀 수 있다.
        steps.initialize();
        steps.process();
        steps.destroy();
    }

}