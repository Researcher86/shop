package com.tanat.shop.utils;

import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Tanat on 16.11.2015.
 */
@Component
public class InitDatabase implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        goodsDao.saveAndFlush(new Goods("Ручка", 5, "Обычная"));
        goodsDao.saveAndFlush(new Goods("Карандаш", 5, "Простой"));
        goodsDao.saveAndFlush(new Goods("Степлер", 50, "Супер"));
        goodsDao.saveAndFlush(new Goods("Тетратдь", 25, "Простая"));
        goodsDao.saveAndFlush(new Goods("Ластик", 5, "Простой"));
    }
}
