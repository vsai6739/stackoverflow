package com.stackoverflow.service;

import com.stackoverflow.repository.TagRepository;
import com.stackoverflow.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepository tagDao;

    TagServiceImpl(TagRepository tagDao){
        this.tagDao=tagDao;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagDao.findAll();
    }

    @Override
    public void saveTag(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    public void deleteTagById(long id) {
        tagDao.deleteById(id);
    }
}
