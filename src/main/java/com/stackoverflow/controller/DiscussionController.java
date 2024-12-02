package com.stackoverflow.controller;

import com.stackoverflow.model.Discussion;
import com.stackoverflow.service.DiscussionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DiscussionController {

    private final DiscussionService discussionService;
    DiscussionController(DiscussionService discussionService){
        this.discussionService=discussionService;
    }

    @GetMapping("/discussion")
    public String getDiscussion(Model model){
        List<Discussion> discussions=discussionService.getAllDiscussion();
        model.addAttribute("discussions",discussions);
        return "discussion/Discussion";
    }

    @PostMapping("/create")
    public  String creatDiscussion(Model model){
        Discussion discussion=new Discussion();
        model.addAttribute(discussion);
        return "discussion/CreateDiscussion";
    }
}
