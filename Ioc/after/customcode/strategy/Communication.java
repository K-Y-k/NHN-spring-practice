package org.example.customcode.strategy;

import org.example.customcode.farewell.Farewell;
import org.example.customcode.greeting.Greeting;
import org.example.customcode.sender.Request;
import org.example.customcode.sender.Sender;
import org.example.framework.annotations.Part;
import org.example.framework.factory.Factory;
import org.example.framework.strategy.Steps;


@Part
public class Communication implements Steps {

    private final Sender sender;
    private final Greeting greeting;
    private final Farewell farewell;

    public Communication() throws Exception {
        this.sender = Factory.getInstance(Sender.class);
        this.greeting = Factory.getInstance(Greeting.class);
        this.farewell = Factory.getInstance(Farewell.class);
    }


    public void initialize() throws Exception {
        greeting.sayHello();

    }

    public void process() {
        //TODO-3 이름과 작성하고 싶은 메시지를 채운다.
        Request request = new Request("김용경", "반갑습니다.");
        sender.sendMessage(request);
    }

    public void destroy() {
        farewell.sayGoodBye();
    }
}
