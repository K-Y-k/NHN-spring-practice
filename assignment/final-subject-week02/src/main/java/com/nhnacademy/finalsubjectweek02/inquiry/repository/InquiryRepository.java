package com.nhnacademy.finalsubjectweek02.inquiry.repository;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {
    Inquiry saveInquiry(Inquiry inquiry);
    Inquiry findById(long id);
    List<Inquiry> getInquiryListByNotAnswered();
    List<Inquiry> getInquiryListByNotAnsweredAndClassification(String classification);
    List<Inquiry> getInquiryListByCustomerId(String customerId);
    List<Inquiry> getInquiryListByClassification(String customerId, String classification);

    boolean existsInquiry(long id);
}
