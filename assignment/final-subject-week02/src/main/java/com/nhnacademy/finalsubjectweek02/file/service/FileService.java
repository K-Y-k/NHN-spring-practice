package com.nhnacademy.finalsubjectweek02.file.service;

import com.nhnacademy.finalsubjectweek02.file.domain.Files;

import java.io.File;
import java.util.List;

public interface FileService {
    Files saveFile(Files file);
    List<Files> getFilesByInquiryId(long inquiryId);
}
