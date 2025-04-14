package com.nhnacademy.restcontrollerpractice.step6.domain;

import java.util.List;

public class DoorayMessengerRequest {
    private String botName;
    private String text;
    private List<Attachment> attachments;

    public DoorayMessengerRequest(String botName, String text, List<Attachment> attachments) {
        this.botName = botName;
        this.text = text;
        this.attachments = attachments;
    }

    public String getBotName() {
        return botName;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

}
