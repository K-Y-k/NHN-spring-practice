package com.nhnacademy.restcontrollerpractice.step6.domain;

import java.util.List;

public class MyServerSendRequest {
    String text;
    Channel channel;
    List<Attachment> attachments;

    public String getText() {
        return text;
    }

    public Channel getChannel() {
        return channel;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }
}
