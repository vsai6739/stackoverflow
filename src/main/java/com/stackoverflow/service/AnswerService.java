package com.stackoverflow.service;

import com.stackoverflow.model.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAnswersByQuestionId(long questionId);
    Answer findAnswerById(long id);
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Long id, Answer updatedAnswer);
    void deleteAnswerById(long id);
    List<Answer> findAllAnswers();
}