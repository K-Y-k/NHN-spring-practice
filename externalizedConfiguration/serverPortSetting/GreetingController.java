package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO-2 @RestController, @GetMapping 을 이용해서 간단한 api 를 만들어본다.
@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String greeting() {
        return "greeting";
    }

}
