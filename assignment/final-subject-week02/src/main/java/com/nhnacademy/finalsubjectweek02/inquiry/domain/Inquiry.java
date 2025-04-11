package com.nhnacademy.finalsubjectweek02.inquiry.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Inquiry {
    private long id;
    private String customerId;
    private String classification;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private boolean answered;

    public Inquiry(String customerId, String classification, String title, String content, String writer) {
        this.customerId = customerId;
        this.classification = classification;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = LocalDateTime.now();
        this.answered = false;
    }

    public static Inquiry createInquiry(String customerId, String classification, String title, String content, String writer) {
        return new Inquiry(customerId, classification, title, content, writer);
    }

    public void setId(long sequenceId) {
        this.id = sequenceId;
    }
}
