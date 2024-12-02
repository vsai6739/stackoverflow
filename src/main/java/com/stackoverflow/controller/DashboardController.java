package com.stackoverflow.controller;

import com.stackoverflow.model.Question;
import com.stackoverflow.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    private final QuestionService questionService;

    DashboardController(QuestionService questionService){
        this.questionService=questionService;
    }
    @GetMapping("/home")
    public String homePage(Model model){
        List<Question> questionList=questionService.getAllQuestions();
        model.addAttribute("questions",questionList);
        return "dashboard";
    }

//    @GetMapping("/question")
//    public String questionPage(Model model){
//        return "question";
//    }
}
