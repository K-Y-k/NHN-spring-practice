package com.nhnacademy.finalsubjectweek03.messenger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "messengerClient", url = "https://nhnacademy.dooray.com/services/3204376758577275363/4045901689874472590/W0RgKMoPTUO3RejIIJVQcg")
public interface MessengerClient {

    @PostMapping
    void sendNotification(@RequestBody DoorayMessengerRequest request);
}
