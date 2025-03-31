package org.example.greeting;

// @Component 를 사용하는 경우에도 이름을 지정할 수 있다.
//@Component("myCustomBeanName")
public class JapaneseGreeting implements Greeting {

    @Override
    public void sayHello() {
        System.out.println("こんにちは世界");
    }


}

