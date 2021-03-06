package com.tanat.shop.service;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category getById(Long id) {
        Category category = categoryRepository.findOne(id);
        if (category == null) {
            throw new AppException("Категория товара не найдена id = " + id);
        }
        return category;
    }

    public void save(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Transactional
    public void delete(Long id) {
        if (!getById(id).getGoodsList().isEmpty()) {
            throw new AppException("Невозможно удалить категорию. На данную категорию ссылаются товары");
        }
        categoryRepository.delete(id);
    }
}
