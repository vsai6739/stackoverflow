package com.stackoverflow.service;

import com.stackoverflow.model.Comment;
import com.stackoverflow.model.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> findAllTags();
    public void saveTag(Tag tag);
    public void deleteTagById(long id);

}
