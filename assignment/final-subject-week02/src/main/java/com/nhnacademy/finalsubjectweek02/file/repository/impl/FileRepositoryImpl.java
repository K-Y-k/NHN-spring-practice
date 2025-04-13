package com.nhnacademy.finalsubjectweek02.file.repository.impl;

import com.nhnacademy.finalsubjectweek02.file.domain.Files;
import com.nhnacademy.finalsubjectweek02.file.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;

@Repository
public class FileRepositoryImpl implements FileRepository {
    private Map<Long, Files> fileMap = new HashMap<>();
    private long sequence = 0L;

    private final String UPLOAD_DIR = "src/main/upload/";

    public FileRepositoryImpl() {
        File directory = new File(UPLOAD_DIR);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (Objects.nonNull(files)) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }

    @Override
    public Files saveFile(Files file) {
        file.setId(++sequence);
        return fileMap.put(file.getId(), file);
    }

    @Override
    public List<Files> findFilesByInquiryId(long inquiryId) {
        List<Files> findFileList = new ArrayList<>();

        for (Map.Entry<Long, Files> file : fileMap.entrySet()) {
            if (file.getValue().getInquiryId() == inquiryId) {
                findFileList.add(file.getValue());
            }
        }

        return findFileList;
    }
}
