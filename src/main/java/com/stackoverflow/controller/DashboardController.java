package com.stackoverflow.controller;

import com.stackoverflow.model.Question;
import com.stackoverflow.model.User;
import com.stackoverflow.service.QuestionService;
import com.stackoverflow.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    private final QuestionService questionService;
    private final UserService userService;

    DashboardController(QuestionService questionService,
                        UserService userService){
        this.questionService=questionService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("user", null);
            return "/dashboard";
        }
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("user", user);
        model.addAttribute("questions",questions);
        return "/dashboard";
    }
}
