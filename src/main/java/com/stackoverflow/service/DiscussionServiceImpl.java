package com.stackoverflow.service;

import com.stackoverflow.model.Discussion;
import com.stackoverflow.repository.CommentRepository;
import com.stackoverflow.repository.DiscussionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService{

    private final DiscussionRepository discussionRepository;
    DiscussionServiceImpl(DiscussionRepository discussionRepository){
        this.discussionRepository=discussionRepository;
    }

    @Override
    public Discussion saveDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion getDiscussionById(Long id) {
        return discussionRepository.findById(id).get();
    }

    @Override
    public List<Discussion> getAllDiscussion() {
        return discussionRepository.findAll();
    }
}
