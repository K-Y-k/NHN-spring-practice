package com.nhnacademy.finalsubjectweek02.file.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Files {
    private long id;
    private long inquiryId;
    private String originalFileName;
    private String uploadFileName;
    private String path;

    public Files(long inquiryId, String originalFileName, String uploadFileName, String path) {
        this.inquiryId = inquiryId;
        this.originalFileName = originalFileName;
        this.uploadFileName = uploadFileName;
        this.path = path;
    }

    public void setId(long sequenceId) {
        this.id = sequenceId;
    }

}
