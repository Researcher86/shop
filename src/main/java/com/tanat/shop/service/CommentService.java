package com.tanat.shop.service;

import com.tanat.shop.dao.CommentDao;
import com.tanat.shop.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    public Comment getById(Long id) {
        return commentDao.findOne(id);
    }

    public void delete(Comment comment) {
        commentDao.delete(comment);
    }
}
