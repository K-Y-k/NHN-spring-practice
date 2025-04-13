package com.nhnacademy.finalsubjectweek02.answer.service.impl;

import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;
import com.nhnacademy.finalsubjectweek02.answer.repository.AnswerRepository;
import com.nhnacademy.finalsubjectweek02.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.saveAnswer(answer);
    }

    @Override
    public Answer getAnswerByInquiryId(long inquiryId) {
        return answerRepository.findByInquiryId(inquiryId);
    }
}
