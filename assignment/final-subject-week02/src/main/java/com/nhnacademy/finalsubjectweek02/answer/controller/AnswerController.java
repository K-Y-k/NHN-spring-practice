package com.nhnacademy.finalsubjectweek02.answer.controller;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.service.AdminService;
import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;
import com.nhnacademy.finalsubjectweek02.answer.domain.dto.AnswerRequest;
import com.nhnacademy.finalsubjectweek02.answer.service.AnswerService;
import com.nhnacademy.finalsubjectweek02.common.exception.ValidationFailedException;
import com.nhnacademy.finalsubjectweek02.file.domain.Files;
import com.nhnacademy.finalsubjectweek02.file.service.FileService;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
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

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin")
public class AnswerController {
    private final AdminService adminService;
    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final FileService fileService;

    @GetMapping("/answer/{inquiryId}")
    public String answerRegisterForm(@PathVariable String inquiryId, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        String adminId = (String) session.getAttribute("userId");
        model.addAttribute("adminId", adminId);

        long inquiryIdParam = Long.parseLong(inquiryId);
        Inquiry inquiry = inquiryService.getInquiry(inquiryIdParam);
        model.addAttribute("inquiry", inquiry);
        log.info("answerRegisterForm: inquiry={}", inquiry);

        List<Files> fileList = fileService.getFilesByInquiryId(inquiryIdParam);
        model.addAttribute("fileList", fileList);

        return "answer/answerRegister";
    }

    @PostMapping("/answer/{inquiryId}")
    public String answerRegister(@PathVariable long inquiryId,
                                 @Valid @ModelAttribute AnswerRequest answerRequest, BindingResult bindingResult,
                                 HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String adminId = (String) req.getSession().getAttribute("userId");
        Admin findAdmin = adminService.getAdmin(adminId);
        answerService.saveAnswer(new Answer(inquiryId, adminId, answerRequest.getComment(),findAdmin.getName(), LocalDateTime.now()));

        Inquiry findInquiry = inquiryService.getInquiry(inquiryId);
        findInquiry.setAnswered(true);

        return "redirect:/cs/admin/" + adminId;
    }
}
