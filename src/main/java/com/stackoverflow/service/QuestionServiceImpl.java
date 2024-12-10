package com.stackoverflow.service;

import com.stackoverflow.dto.QuestionRequestDTO;
import com.stackoverflow.model.Answer;
import com.stackoverflow.model.Question;
import com.stackoverflow.model.Tag;
import com.stackoverflow.model.User;
import com.stackoverflow.repository.AnswerRepository;
import com.stackoverflow.repository.QuestionRepository;
import com.stackoverflow.repository.TagRepository;
import com.stackoverflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final AnswerService answerService;

    public QuestionServiceImpl(QuestionRepository questionRepository, TagRepository tagRepository, AnswerRepository answerRepository,
                               ModelMapper modelMapper, UserServiceImpl userService, UserRepository userRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.answerService = answerService;
    }

//    @Override
//    @Transactional
//    public Question createQuestion(QuestionRequestDTO questionRequestDTO) {
//        Question question = modelMapper.map(questionRequestDTO, Question.class);
//        User user=userService.getUserById(question.getUser().getId());
//        question.setUser(user);
//
//        Set<Tag> tags = questionRequestDTO.getTagsList().stream()
//                .map(tagName -> tagRepository.findByName(tagName).orElseGet(() -> new Tag(tagName)))
//                .collect(Collectors.toSet());
//
//        question.setTags(tags);
//
//        Question updatedQuestion = questionRepository.save(question);
//        return updatedQuestion;
//    }
@Override
@Transactional
public Question createQuestion(QuestionRequestDTO questionRequestDTO) {
    Question question = modelMapper.map(questionRequestDTO, Question.class);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
        throw new IllegalStateException("User must be logged in to create a question.");
    }

    String email = authentication.getName();
    User user = userService.getUserByEmail(email);
    question.setUser(user);

    Set<Tag> tags = questionRequestDTO.getTagsList().stream()
            .map(tagName -> tagRepository.findByName(tagName).orElseGet(() -> new Tag(tagName)))
            .collect(Collectors.toSet());
    question.setTags(tags);

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

    @Override
    public void updateQuestion(Long id, Question question){
        question.setUpdatedAt(LocalDateTime.now());
        questionRepository.save(question);
    }
}
