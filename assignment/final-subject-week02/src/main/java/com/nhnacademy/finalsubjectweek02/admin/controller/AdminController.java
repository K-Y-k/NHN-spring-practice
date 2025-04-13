package com.nhnacademy.finalsubjectweek02.admin.controller;

import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class AdminController {
    private final InquiryService inquiryService;


    @GetMapping("/admin/{adminId}")
    public String adminMain(@PathVariable String adminId, Model model) {
        List<Inquiry> inquiryListByNotAnswered = inquiryService.getInquiryListByNotAnswered();

        model.addAttribute("inquiryList", inquiryListByNotAnswered);
        model.addAttribute("adminId", adminId);

        log.info("inquiryListByNotAnswered = {}", inquiryListByNotAnswered);
        return "admin/adminMain";
    }

    @GetMapping("/admin/{adminId}/search")
    public String adminMain(@PathVariable String adminId,
                            @RequestParam String classification, Model model) {
        List<Inquiry> inquiryListByNotAnsweredAndClassification = inquiryService.getInquiryListByNotAnsweredAndClassification(classification);

        model.addAttribute("inquiryList", inquiryListByNotAnsweredAndClassification);
        model.addAttribute("adminId", adminId);
        model.addAttribute("classification", classification);

        log.info("inquiryListByNotAnsweredAndClassification = {}", inquiryListByNotAnsweredAndClassification);
        return "admin/adminMain";
    }

}
