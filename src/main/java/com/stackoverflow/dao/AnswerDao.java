package com.stackoverflow.dao;

import com.stackoverflow.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerDao extends JpaRepository<Answer,Long> {

}
