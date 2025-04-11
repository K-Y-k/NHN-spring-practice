package com.nhnacademy.finalsubjectweek02.inquiry.repository;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {
    Inquiry saveInquiry(Inquiry inquiry);
    Inquiry findById(long id);
    List<Inquiry> getInquiryListByCustomerId(String customerId);

    boolean existsInquiry(long id);
}
