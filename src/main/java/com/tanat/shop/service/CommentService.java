package com.tanat.shop.service;

import com.tanat.shop.model.Comment;
import com.tanat.shop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return repository.findAll(new Sort(Sort.Direction.DESC, "date"));
    }
}
