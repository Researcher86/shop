package com.tanat.shop.service;

import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> getAll() {
        return goodsDao.findAll();
    }

    public Goods getById(Long id) {
        return goodsDao.findOne(id);
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

    @Transactional
    public Goods addCommentForGoods(Comment comment, Long id) {
        Goods goods = goodsDao.findOne(id);
        goods.getComments().size();
        goods.addComments(comment);

        Goods storeGoods = goodsDao.save(goods);
        storeGoods.getComments().size();
        return storeGoods;
    }

    public Page<Goods> getGoodsLog(Integer pageNumber, Integer pageSize) {
        return goodsDao.findAll(new PageRequest(pageNumber - 1, pageSize));
    }

    public Page<Goods> getGoodsLog2(Pageable p) {
        return goodsDao.findAll(p);
    }

    public Page<Goods> getGoodsLogByCategory(Integer pageNumber, Integer pageSize, Long id) {
        return goodsDao.findGoodsByCategory(id, new PageRequest(pageNumber - 1, pageSize));
    }
}
