package com.stackoverflow.service;

import com.stackoverflow.model.Answer;
import com.stackoverflow.model.Notification;
import com.stackoverflow.model.Question;
import com.stackoverflow.model.User;

import java.util.List;

public interface UserService {
    User registerUser(String username, String email, String password);
    User authenticateUser(String email, String password);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User updateUserProfile(Long userId, String bio);
    List<Question> getQuestionsByUser(Long userId);
    List<Answer> getAnswersByUser(Long userId);
    List<Notification> getNotificationsByUser(Long userId);
    void deleteUser(Long userId);
}
