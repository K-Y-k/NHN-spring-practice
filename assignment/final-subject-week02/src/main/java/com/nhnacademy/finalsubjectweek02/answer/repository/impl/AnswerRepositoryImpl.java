package com.nhnacademy.finalsubjectweek02.answer.repository.impl;

import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;
import com.nhnacademy.finalsubjectweek02.answer.repository.AnswerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
    private final Map<Long, Answer> answerMap = new HashMap<>();


    @Override
    public Answer saveAnswer(Answer answer) {
        return answerMap.put(answer.getInquiryId(), answer);
    }

    @Override
    public Answer findByInquiryId(long inquiryId) {
        return answerMap.get(inquiryId);
    }
}
