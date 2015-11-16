package com.tanat.shop.service;

import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanat on 16.11.2015.
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> getAll() {
        return goodsDao.findAll();
    }


}
