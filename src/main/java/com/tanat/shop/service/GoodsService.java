package com.tanat.shop.service;

import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import com.tanat.shop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Transactional(readOnly = true)
    public Goods getById(Long id) {
        return goodsRepository.findOne(id);
    }

    @Transactional
    public Goods getByIdAndAllActiveComments(Long id) {
        Goods goods = goodsRepository.findOne(id);

        List<Comment> activeComments = goods.getComments().stream()
                .filter(comment -> comment.isActive())
                .collect(Collectors.toList());

        goods.setComments(activeComments);

        return goods;
    }

    public void save(Goods goods) {
        goodsRepository.saveAndFlush(goods);
    }

    public void delete(Long id) {
        goodsRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Goods> findByName(String str) {
        return goodsRepository.findByNameLike("%" + str + "%");
    }

    @Transactional
    public Goods addCommentForGoods(Comment comment, Long id) {
        Goods goods = goodsRepository.findOne(id);
        goods.addComments(comment);
        return goods;
    }

    @Transactional(readOnly = true)
    public Page<Goods> getGoodsLog(Integer pageNumber, Integer pageSize) {
        return goodsRepository.findAll(new PageRequest(pageNumber - 1, pageSize));
    }

    @Transactional(readOnly = true)
    public Page<Goods> getGoodsLogByCategory(Integer pageNumber, Integer pageSize, Long categoryId) {
        return goodsRepository.findGoodsByCategory(categoryId, new PageRequest(pageNumber - 1, pageSize));
    }

    @Transactional(readOnly = true)
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }
}
