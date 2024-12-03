package com.stackoverflow.controller;

import com.stackoverflow.dto.AnswerRequestDTO;
import com.stackoverflow.dto.CommentRequestDTO;
import com.stackoverflow.dto.QuestionRequestDTO;
import com.stackoverflow.model.Answer;
import com.stackoverflow.model.Question;
import com.stackoverflow.service.CommentService;
import com.stackoverflow.service.QuestionService;
import com.stackoverflow.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final UserServiceImpl userService;
    private final CommentService commentService;

    public QuestionController(QuestionService questionService, UserServiceImpl userService, CommentService commentService) {
        this.questionService = questionService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        List<Question> questionList=questionService.getAllQuestions();
        model.addAttribute("questions",questionList);
        return "dashboard";
    }

    @GetMapping("/ask")
    public String questionPage(Model model){
        model.addAttribute("questionRequestDTO", new QuestionRequestDTO());
        return "question/create";
    }

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute("questionRequestDTO") QuestionRequestDTO questionRequestDTO) {
        System.out.println("Tags List: " + questionRequestDTO.getTagsList());

        Question createdQuestion = questionService.createQuestion(questionRequestDTO);
        //return "redirect:/questions/home";
        return "redirect:/users/dashboard";
    }

    @GetMapping("/{id}")
    public String getQuestionById(@PathVariable("id") Long questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);

        model.addAttribute("question", question);
        return "question/detail";
    }
  
    @GetMapping("upvote/{id}")
    public String updateUpvote(@PathVariable("id") Long id){
        Question question=questionService.getQuestionById(id);
        Integer upvote=question.getUpvotes()+1;
        question.setUpvotes(upvote);
        questionService.updateQuestion(id,question);
        return "redirect:/questions/" + id;
    }

    @GetMapping("downvote/{id}")
    public String updateDownvote(@PathVariable("id") Long id){
        Question question=questionService.getQuestionById(id);
        Integer downvote=question.getDownvotes()+1;
        question.setDownvotes(downvote);
        questionService.updateQuestion(id,question);
        return "redirect:/questions/" + id;
    }
}
