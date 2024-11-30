package com.stackoverflow.service;

import com.stackoverflow.dao.TagDao;
import com.stackoverflow.model.Tag;

import java.util.List;

public class TagServiceImple implements TagService{
    private final TagDao tagDao;

    TagServiceImple(TagDao tagDao){
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
