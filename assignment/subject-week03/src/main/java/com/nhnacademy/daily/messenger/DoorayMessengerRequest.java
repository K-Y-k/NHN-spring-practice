package com.nhnacademy.daily.messenger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoorayMessengerRequest {
    private String botName;
    private String text;
}
