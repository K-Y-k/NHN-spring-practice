package com.nhnacademy.finalsubjectweek02.file.repository;


import com.nhnacademy.finalsubjectweek02.file.domain.Files;

import java.util.List;

public interface FileRepository {
    Files saveFile(Files file);
    List<Files> findFilesByInquiryId(long inquiryId);
}
