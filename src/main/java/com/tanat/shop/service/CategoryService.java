package com.tanat.shop.service;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Category getById(Long id) {
        Category category = categoryDao.findOne(id);
        if (category == null) {
            throw new AppException("Категория товара не найдена id = " + id);
        }

        category.getGoodsList().size();

        return category;
    }

    public void addGoodsInCategory(Goods goods, Category category) {
        category.addGoods(goods);
        categoryDao.saveAndFlush(category);
    }

    public void save(Category category) {
        categoryDao.saveAndFlush(category);
    }

    public void delete(Long id) {
        Category category = getById(id);
        if (category.getGoodsList().size() > 0) {
            throw new AppException("Категория содержит товары и не может быть удалена");
        }
        categoryDao.delete(category.getId());
    }
}
