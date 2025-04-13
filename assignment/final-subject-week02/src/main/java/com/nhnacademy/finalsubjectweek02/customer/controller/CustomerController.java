package com.nhnacademy.finalsubjectweek02.customer.controller;

import com.nhnacademy.finalsubjectweek02.answer.service.AnswerService;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
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
public class CustomerController {
    private final InquiryService inquiryService;


    @GetMapping("/{customerId}")
    public String customerMain(@PathVariable String customerId, Model model) {
        List<Inquiry> inquiryListByCustomerId = inquiryService.getInquiryListByCustomerId(customerId);
        model.addAttribute("inquiryList", inquiryListByCustomerId);
        model.addAttribute("customerId", customerId);
        log.info("inquiryListByCustomerId = {}", inquiryListByCustomerId);

        return "customer/customerMain";
    }

    @GetMapping("/{customerId}/search")
    public String customerMain(@PathVariable String customerId, @RequestParam String classification,
                               Model model) {
        List<Inquiry> inquiryListByClassification = inquiryService.getInquiryListByClassification(customerId, classification);
        model.addAttribute("inquiryList", inquiryListByClassification);
        model.addAttribute("classification", classification);
        log.info("inquiryListByClassification = {}", inquiryListByClassification);

        return "customer/customerMain";
    }
}
