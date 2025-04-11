package com.nhnacademy.finalsubjectweek02.inquiry.repository.impl;

import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.exception.InquiryAlreadyExistsException;
import com.nhnacademy.finalsubjectweek02.inquiry.repository.InquiryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class InquiryRepositoryImpl implements InquiryRepository {
    private final Map<Long, Inquiry> inquiryMap = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Inquiry saveInquiry(Inquiry inquiry) {
        if (existsInquiry(inquiry.getId())) {
            throw new InquiryAlreadyExistsException("Inquiry with id " + inquiry.getId() + " already exists");
        }

        inquiry.setId(++sequence);
        log.info("saveInquiry id = {}", inquiry.getId());
        return inquiryMap.put(inquiry.getId(), inquiry);
    }

    @Override
    public Inquiry findById(long id) {
        log.info("InquiryRepositoryImpl - inquiryMap");
        for (Map.Entry<Long, Inquiry> inquiryEntry : inquiryMap.entrySet()) {
            log.info("inquiryEntry = {}, {}", inquiryEntry.getKey(), inquiryEntry.getValue());
        }

        Inquiry findInquiry = inquiryMap.get(id);
        if (Objects.isNull(findInquiry)) {
            return null;
        }

        return findInquiry;
    }

    @Override
    public List<Inquiry> getInquiryListByCustomerId(String customerId) {
        List<Inquiry> inquiryList = new ArrayList<>();

        for (Inquiry inquiry : inquiryMap.values()) {
            if (inquiry.getCustomerId().equals(customerId)) {
                inquiryList.add(inquiry);
            }
        }

        return inquiryList;
    }

    @Override
    public boolean existsInquiry(long id) {
        return inquiryMap.containsKey(id);
    }
}
