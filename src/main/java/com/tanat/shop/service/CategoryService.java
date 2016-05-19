package com.tanat.shop.service;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Transactional
    public Category getById(Long id) {
        Category category = categoryDao.findOne(id);
        if (category == null) {
            throw new AppException("Категория товара не найдена id = " + id);
        }

        category.getGoodsList().size();

        return category;
    }

    public void save(Category category) {
        categoryDao.saveAndFlush(category);
    }

}
