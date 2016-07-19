package com.tanat.shop.service;

import com.tanat.shop.exception.AppException;
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

    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return repository.findOne(id);
    }

    public void save(Comment comment) {
        repository.saveAndFlush(comment);
    }

    public void delete(Long id) {
        if (getById(id) == null) {
            throw new AppException("Произошла ошибка при удалении! Комментарий ненайден.");
        }
        repository.delete(id);
    }
}
