package com.stackoverflow.controller;

import com.stackoverflow.model.Answer;
import com.stackoverflow.model.Question;
import com.stackoverflow.service.AnswerService;
import com.stackoverflow.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping("/{questionId}")
    public String getAnswersByQuestionId(@PathVariable Long questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);
        List<Answer> answers = answerService.findAnswersByQuestionId(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("question", question);
        return "answer_list";
    }

    @GetMapping("/new/{questionId}")
    public String showCreateAnswerForm(@PathVariable Long questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);
        Answer answer = new Answer();
        answer.setQuestion(question);
        model.addAttribute("answer", answer);
        model.addAttribute("question", question);
        return "answer_form";
    }

    @PostMapping("/save/{questionId}")
    public String saveAnswer(@PathVariable Long questionId, @RequestParam("answer") String answer) {
            Question question = questionService.getQuestionById(questionId);
            Answer answers = new Answer();
            answers.setQuestion(question);
            answers.setContent(answer);
            answerService.createAnswer(answers);
            return "redirect:/questions/" + questionId;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateAnswerForm(@PathVariable Long id, Model model) {
        Answer answer = answerService.findAnswerById(id);
        model.addAttribute("answer", answer);
        return "answer_edit_form";
    }

    @PostMapping("/update/{id}")
    public String updateAnswer(@PathVariable Long id, @ModelAttribute("answer") Answer updatedAnswer) {
        answerService.updateAnswer(updatedAnswer);
        return "redirect:/answers/" + updatedAnswer.getQuestion().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        Answer answer = answerService.findAnswerById(id);
        Long questionId = answer.getQuestion().getId();
        answerService.deleteAnswerById(id);
        return "redirect:/answers/" + questionId;
    }

    @GetMapping("upvote/{questionId}/{id}")
    public String updateUpvote(@PathVariable("questionId") Long questionId,@PathVariable("id") Long id ){
        Answer answer=answerService.findAnswerById(id);
        Integer upvote=answer.getUpvotes()+1;
        answer.setUpvotes(upvote);
        answerService.updateAnswer(answer);
        return "redirect:/questions/" + questionId;
    }

    @GetMapping("downvote/{questionId}/{id}")
    public String updateDownvote(@PathVariable("questionId") Long questionId,@PathVariable("id") Long id){
        Answer answer=answerService.findAnswerById(id);
        Integer downvote=answer.getDownvotes()+1;
        answer.setDownvotes(downvote);
        answerService.updateAnswer(answer);
        return "redirect:/questions/" + questionId;
    }
}