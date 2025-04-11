package com.nhnacademy.finalsubjectweek02.inquiry.service;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;

import java.util.List;

public interface InquiryService {
    Inquiry saveInquiry(Inquiry inquiry);
    Inquiry getInquiry(long id);
    List<Inquiry> getInquiryListByCustomerId(String customerId);
}
