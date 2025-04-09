package com.nhnacademy.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

// TODO #5: 파일 업로드 처리
@Controller
public class FileUploadController {
    /**
     * 프로젝트 내의 상대 경로 가능
     */
    private static final String UPLOAD_DIR = "src/main/upload/";

    @PostMapping("/fileUpload")
    public String processUpload(@RequestParam("file") MultipartFile file,
                                Model model) throws IOException {
        file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));

        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("size", file.getSize());

        return "uploadSuccess";
    }
}
