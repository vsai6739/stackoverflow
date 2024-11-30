package com.stackoverflow.service;

import com.stackoverflow.model.Question;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
//    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
//    List<Question> searchQuestions(String title, String tag);
//    List<Question> filterQuestionsByStatus(String status);
}
