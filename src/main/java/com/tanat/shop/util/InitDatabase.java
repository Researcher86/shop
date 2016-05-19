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
            Category category2 = new Category("Ручка");
            categoryDao.saveAndFlush(category2);

            Goods goods = new Goods("Ручка", 5, "Обычная", LoadImage.load("pencel.jpg"));
            goods.setCategory(category2);
            goodsDao.saveAndFlush(goods);

            category2 = new Category("Дырокол");
            categoryDao.saveAndFlush(category2);

            goods = new Goods("Дырокол", 45, "Обычный", LoadImage.load("dirakol.jpg"));
            goods.setCategory(category2);
            goodsDao.saveAndFlush(goods);


            category2 = new Category("Карандаш");
            categoryDao.saveAndFlush(category2);

            goods = new Goods("Карандаш", 45, "Обычный", LoadImage.load("karandash.jpg"));
            goods.setCategory(category2);
            goodsDao.saveAndFlush(goods);


            category2 = new Category("Бумага");
            categoryDao.saveAndFlush(category2);

            goods = new Goods("Бумага", 45, "Обычная", LoadImage.load("bumaga.png"));
            goods.setCategory(category2);
            goodsDao.saveAndFlush(goods);

            categoryDao.saveAndFlush(new Category("Линейка"));
            categoryDao.saveAndFlush(new Category("Папка"));
        }
    }
}
