package com.tanat.shop.service;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanat on 25.11.2015.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    public Category getById(Long id) {
        return categoryDao.findOne(id);
    }

    public void addGoodsInCategory(Goods goods, Category category) {
        category.addGoods(goods);
        categoryDao.saveAndFlush(category);
    }

    public void save(Category category) {
        categoryDao.saveAndFlush(category);
    }
}
