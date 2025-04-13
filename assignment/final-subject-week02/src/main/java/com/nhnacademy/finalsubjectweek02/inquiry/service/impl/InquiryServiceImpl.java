package com.nhnacademy.finalsubjectweek02.inquiry.service.impl;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.exception.InquiryNotFoundException;
import com.nhnacademy.finalsubjectweek02.inquiry.repository.InquiryRepository;
import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;


    @Override
    public Inquiry saveInquiry(Inquiry inquiry) {
        return inquiryRepository.saveInquiry(inquiry);
    }

    @Override
    public Inquiry getInquiry(long id) {
        Inquiry findInquiry = inquiryRepository.findById(id);
        if (Objects.isNull(findInquiry)) {
            throw new InquiryNotFoundException("Inquiry with id " + id + " not found");
        }

        return findInquiry;
    }

    @Override
    public List<Inquiry> getInquiryListByNotAnswered() {
        return inquiryRepository.getInquiryListByNotAnswered();
    }

    @Override
    public List<Inquiry> getInquiryListByNotAnsweredAndClassification(String classification) {
        return inquiryRepository.getInquiryListByNotAnsweredAndClassification(classification);
    }

    @Override
    public List<Inquiry> getInquiryListByCustomerId(String customerId) {
        return inquiryRepository.getInquiryListByCustomerId(customerId);
    }

    @Override
    public List<Inquiry> getInquiryListByClassification(String customerId, String classification) {
        return inquiryRepository.getInquiryListByClassification(customerId, classification);
    }

}
