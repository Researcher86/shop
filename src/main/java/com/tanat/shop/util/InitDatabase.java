package com.tanat.shop.util;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Компонент для иницыализации БД
 * Created by Tanat on 16.11.2015.
 */
@Component
public class InitDatabase implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GoodsDao goodsDao;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (categoryDao.findAll().size() == 0) {
            categoryDao.saveAndFlush(new Category("Электроника"));

            Category category = new Category("Канцтовары");
            categoryDao.saveAndFlush(category);

            Image image = LoadImage.load();

            Goods goods = new Goods("Ручка", 5, "Обычная", image);
            goods.setCategory(category);
            goodsDao.saveAndFlush(goods);

            goods = new Goods("Карандаш", 5, "Простой", image);
            goods.setCategory(category);
            goodsDao.saveAndFlush(goods);

            goods = new Goods("Степлер", 50, "Супер", image);
            goods.setCategory(category);
            goodsDao.saveAndFlush(goods);

            goods = new Goods("Тетрадь", 25, "Простая", image);
            goods.setCategory(category);
            goodsDao.saveAndFlush(goods);

            goods = new Goods("Ластик", 5, "Простой", image);
            goods.setCategory(category);
            goodsDao.saveAndFlush(goods);
        }
    }
}
