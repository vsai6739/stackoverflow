package com.stackoverflow.service;

import com.stackoverflow.dao.AnswerDao;
import com.stackoverflow.model.Answer;

import java.util.List;

public class AnswerServiceImple implements AnswerService{

    private final AnswerDao answerDao;
    AnswerServiceImple(AnswerDao answerDao){
        this.answerDao=answerDao;
    }
    @Override
    public List<Answer> findAnswerByQuestionId(long questionId) {
        return List.of();
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerDao.save(answer);
    }

    @Override
    public void deleteAnswerById(long id) {
        answerDao.deleteById(id);
    }

    @Override
    public List<Answer> findAllAnswer() {
        return answerDao.findAll();
    }
}
