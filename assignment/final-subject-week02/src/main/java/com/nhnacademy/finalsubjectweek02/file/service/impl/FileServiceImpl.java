package com.nhnacademy.finalsubjectweek02.file.service.impl;

import com.nhnacademy.finalsubjectweek02.file.domain.Files;
import com.nhnacademy.finalsubjectweek02.file.repository.FileRepository;
import com.nhnacademy.finalsubjectweek02.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public Files saveFile(Files file) {
        return fileRepository.saveFile(file);
    }

    @Override
    public List<Files> getFilesByInquiryId(long inquiryId) {
        return fileRepository.findFilesByInquiryId(inquiryId);
    }
}
