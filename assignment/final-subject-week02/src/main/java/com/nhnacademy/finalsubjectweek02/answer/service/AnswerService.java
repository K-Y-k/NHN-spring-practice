package com.nhnacademy.finalsubjectweek02.answer.service;

import com.nhnacademy.finalsubjectweek02.answer.domain.Answer;

public interface AnswerService {
    Answer saveAnswer(Answer answer);
    Answer getAnswerByInquiryId(long inquiryId);
}
