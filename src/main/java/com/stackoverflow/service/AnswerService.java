package com.stackoverflow.service;

import com.stackoverflow.model.Answer;

import java.util.List;

public interface AnswerService {
    public List<Answer> findAnswerByQuestionId(long questionId);
    public void saveAnswer(Answer answer);
    public void deleteAnswerById(long id);
    public List<Answer> findAllAnswer();
}
