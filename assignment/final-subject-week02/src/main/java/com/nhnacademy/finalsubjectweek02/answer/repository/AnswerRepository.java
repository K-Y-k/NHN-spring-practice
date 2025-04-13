package com.nhnacademy.finalsubjectweek02.answer.repository;

import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;

public interface AnswerRepository {
    Answer saveAnswer(Answer answer);
    Answer findByInquiryId(long inquiryId);
}
