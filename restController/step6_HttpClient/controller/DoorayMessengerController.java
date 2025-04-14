package com.nhnacademy.restcontrollerpractice.step6.controller;

import com.nhnacademy.restcontrollerpractice.step6.service.MessengerService;
import com.nhnacademy.restcontrollerpractice.step6.domain.MyServerSendRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DoorayMessengerController {

    private final MessengerService messengerService = new MessengerService();

    @PostMapping("/send-message")
    public ResponseEntity sendMessage(@RequestBody MyServerSendRequest sendRequest) {
        messengerService.sendMessage(sendRequest);
        return ResponseEntity.ok().build();
    }
}
