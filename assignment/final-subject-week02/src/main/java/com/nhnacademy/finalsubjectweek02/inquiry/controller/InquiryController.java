package com.nhnacademy.finalsubjectweek02.inquiry.controller;

import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;
import com.nhnacademy.finalsubjectweek02.answer.service.AnswerService;
import com.nhnacademy.finalsubjectweek02.common.exception.ValidationFailedException;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
import com.nhnacademy.finalsubjectweek02.file.domain.Files;
import com.nhnacademy.finalsubjectweek02.file.service.FileService;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.dto.InquiryRequest;
import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class InquiryController {
    private final String UPLOAD_DIR = "src/main/upload/";

    private final CustomerService customerService;
    private final InquiryService inquiryService;
    private final FileService fileService;
    private final AnswerService answerService;

    @GetMapping("/inquiry/detail")
    public String inquiryView(@RequestParam String inquiryId,
                              HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        String customerId = (String) session.getAttribute("userId");
        model.addAttribute("customerId", customerId);

        long inquiryIdParam = Long.parseLong(inquiryId);
        Inquiry inquiry = inquiryService.getInquiry(inquiryIdParam);
        model.addAttribute("inquiry", inquiry);
        log.info("inquiryView: inquiry={}", inquiry);

        List<Files> fileList = fileService.getFilesByInquiryId(inquiryIdParam);
        model.addAttribute("fileList", fileList);

        Answer answer = answerService.getAnswerByInquiryId(inquiryIdParam);
        model.addAttribute("answer", answer);

        return "inquiry/inquiryView";
    }

    @GetMapping("/inquiry")
    public String inquiryRegisterForm(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        String customerId = (String) session.getAttribute("userId");
        model.addAttribute("customerId", customerId);

        return "inquiry/inquiryRegister";
    }

    @PostMapping("/inquiry")
    public String inquiryRegister(@Valid @ModelAttribute InquiryRequest inquiryRequest,
                                  BindingResult bindingResult,
                                  HttpServletRequest req) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        HttpSession session = req.getSession(false);
        String customerId = (String) session.getAttribute("userId");
        String writer = customerService.getCustomer(customerId).getId();

        String classification = inquiryRequest.getClassification();
        String title = inquiryRequest.getTitle();
        String content = inquiryRequest.getContent();

        log.info("inquiryRequest = {}", inquiryRequest);

        Inquiry saveInquiry = Inquiry.createInquiry(customerId, classification, title, content, writer);
        inquiryService.saveInquiry(saveInquiry);


        List<MultipartFile> files = inquiryRequest.getFiles();
        for (MultipartFile file : files) {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String uploadFileName = uuid + "_" + originalFilename;
            String path = UPLOAD_DIR + uploadFileName;

            file.transferTo(Paths.get(path));
            fileService.saveFile(new Files(saveInquiry.getId(), originalFilename, uploadFileName, path));
        }

        return "redirect:/cs/" + customerId;
    }
}
