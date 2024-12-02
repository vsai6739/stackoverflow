package com.stackoverflow.controller;

import com.stackoverflow.dto.UserLoginRequest;
import com.stackoverflow.model.Answer;
import com.stackoverflow.model.Notification;
import com.stackoverflow.model.Question;
import com.stackoverflow.model.User;
import com.stackoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/registerationform";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
            return "redirect:/users/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new UserLoginRequest());
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("loginRequest") UserLoginRequest request, Model model) {
        try {
            User user = userService.login(request.getEmail(), request.getPassword());
            model.addAttribute("user", user);
            return "redirect:/users/profile/" + user.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/login";
        }
    }

    @GetMapping("/profile/{userId}")
    public String getUserProfile(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/{userId}/profile/edit")
    public String showUpdateProfileForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/editprofile";
    }

    @PatchMapping("/{userId}/profile/update")
    public String updateUserProfile(@PathVariable Long userId, @ModelAttribute("user") User user) {
        userService.updateUserProfile(userId, user.getProfile().getBio());
        return "redirect:/users/profile/" + userId;
    }

    @GetMapping("/{userId}/questions")
    public String getUserQuestions(@PathVariable Long userId, Model model) {
        List<Question> questions = userService.getQuestionsByUser(userId);
        model.addAttribute("questions", questions);
        return "user/questions";
    }

    @GetMapping("/{userId}/answers")
    public String getUserAnswers(@PathVariable Long userId, Model model) {
        List<Answer> answers = userService.getAnswersByUser(userId);
        model.addAttribute("answers", answers);
        return "user/answers";
    }

    @GetMapping("/{userId}/notifications")
    public String getUserNotifications(@PathVariable Long userId, Model model) {
        List<Notification> notifications = userService.getNotificationsByUser(userId);
        model.addAttribute("notifications", notifications);
        return "user/notifications";
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
