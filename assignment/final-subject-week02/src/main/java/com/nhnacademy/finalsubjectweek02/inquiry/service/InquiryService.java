package com.nhnacademy.finalsubjectweek02.inquiry.service;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;

import java.util.List;

public interface InquiryService {
    Inquiry saveInquiry(Inquiry inquiry);
    Inquiry getInquiry(long id);
    List<Inquiry> getInquiryListByNotAnswered();
    List<Inquiry> getInquiryListByNotAnsweredAndClassification(String classification);
    List<Inquiry> getInquiryListByCustomerId(String customerId);
    List<Inquiry> getInquiryListByClassification(String customerId, String classification);
}
