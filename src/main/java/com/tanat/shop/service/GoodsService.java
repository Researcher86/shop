package com.tanat.shop.service;

import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

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

    public void save(Goods goods) {
        goodsDao.saveAndFlush(goods);
    }

    public void delete(Long id) {
        goodsDao.delete(getById(id).getId());
    }

    public List<Goods> findByName(String str) {
        return goodsDao.findByNameLike("%" + str + "%");
    }
}
