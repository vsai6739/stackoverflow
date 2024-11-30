package com.stackoverflow.service;

import com.stackoverflow.dao.CommentDao;
import com.stackoverflow.model.Comment;

import java.util.List;

public class CommentServiceImple implements CommentService{

    private final CommentDao commentDao;
    CommentServiceImple(CommentDao commentDao){
        this.commentDao=commentDao;
    }

    @Override
    public List<Comment> findAllComments() {
        return commentDao.findAll();
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
    }
}
