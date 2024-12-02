package com.stackoverflow.service;

import com.stackoverflow.model.Discussion;
import com.stackoverflow.model.Question;

import java.util.List;

public interface DiscussionService {
    Discussion saveDiscussion(Discussion discussion);
    Discussion getDiscussionById(Long id);
    List<Discussion> getAllDiscussion();
}
