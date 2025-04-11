package com.nhnacademy.finalsubjectweek02.inquiry.controller;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.InquiryRequest;
import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class InquiryController {
    private static final String UPLOAD_DIR = "src/main/upload/";

    private final InquiryService inquiryService;
    private final CustomerService customerService;

    @GetMapping("/inquiry/detail")
    public String inquiryView(@CookieValue(value = "SESSION", required = false) String cookieSessionId,
                              HttpServletRequest req,
                              @RequestParam String inquiryId, Model model) {
        HttpSession session = req.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }
        if (!cookieSessionId.equals(sessionId)) {
            return "redirect:/login";
        }

        long inquiryIdParam = Long.parseLong(inquiryId);
        Inquiry inquiry = inquiryService.getInquiry(inquiryIdParam);
        model.addAttribute("inquiry", inquiry);
        log.info("inquiryView: inquiry={}", inquiry);

        return "inquiry/inquiryView";
    }

    @GetMapping("/inquiry")
    public String inquiryRegisterForm(@CookieValue(value = "SESSION", required = false) String cookieSessionId,
                                      HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }
        if (!cookieSessionId.equals(sessionId)) {
            return "redirect:/login";
        }

        return "inquiry/inquiryRegister";
    }

    @PostMapping("/inquiry")
    public String inquiryRegister(@CookieValue(value = "SESSION", required = false) String cookieSessionId,
                                  HttpServletRequest req,
                                  InquiryRequest inquiryRequest) {
        HttpSession session = req.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }
        if (!cookieSessionId.equals(sessionId)) {
            return "redirect:/login";
        }

        String customerId = (String) session.getAttribute("userId");
        String writer = customerService.getCustomer(customerId).getId();

        String classification = inquiryRequest.getClassification();
        String title = inquiryRequest.getTitle();
        String content = inquiryRequest.getContent();

        log.info("inquiryRequest = {}", inquiryRequest);

        inquiryService.saveInquiry(Inquiry.createInquiry(customerId, classification, title, content, writer));

        return "redirect:/cs/" + customerId;
    }
}
