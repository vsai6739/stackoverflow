package com.stackoverflow.service;

import com.stackoverflow.model.Question;
import com.stackoverflow.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository=questionRepository;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        Optional<Question> question=questionRepository.findById(id);
        return question.get();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
