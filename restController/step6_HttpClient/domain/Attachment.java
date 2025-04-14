package com.nhnacademy.restcontrollerpractice.step6.domain;

public class Attachment {
    String title;
    String text;
    String titleLink;
    String botIconImage;
    String color;

    public Attachment(String title, String text, String titleLink, String botIconImage, String color) {
        this.title = title;
        this.text = text;
        this.titleLink = titleLink;
        this.botIconImage = botIconImage;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public String getBotIconImage() {
        return botIconImage;
    }

    public String getColor() {
        return color;
    }
}
