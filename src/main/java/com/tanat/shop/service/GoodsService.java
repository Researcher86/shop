package com.tanat.shop.service;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CategoryDao categoriesDao;

    public List<Goods> getAll() {
        return goodsDao.findAll();
    }

    public Goods getById(Long id) {
        Goods goods = goodsDao.findOne(id);
        if (goods == null) {
            throw new AppException("Товар не найден id = " + id);
        }
        return goods;
    }

    @Transactional
    public Goods getByIdAndAllComments(Long id) {
        Goods goods = getById(id);
        goods.getComments().size();
        return goods;
    }

    public void save(Goods goods) {
        goodsDao.saveAndFlush(goods);
    }

    public void delete(Goods goods) {
        goodsDao.delete(goods);
    }

    public List<Goods> findByName(String str) {
        return goodsDao.findByNameLike("%" + str + "%");
    }

    public List<Goods> findByCategory(Category category) {
        return goodsDao.findByCategory(category);
    }

}
