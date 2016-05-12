package com.tanat.shop.util;

import com.tanat.shop.dao.CategoryDao;
import com.tanat.shop.dao.GoodsDao;
import com.tanat.shop.dao.ImageDao;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Autowired
    private ImageDao imageDao;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (categoryDao.findAll().size() == 0) {
            categoryDao.saveAndFlush(new Category("Электроника"));

            Category category = new Category("Канцтовары");
            categoryDao.saveAndFlush(category);

            File file = null;
            byte[] fileArray = new byte[0];

            try {
                file = ResourceUtils.getFile("classpath:bumaga.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fileArray = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image image = new Image(fileArray, StringUtils.getFilenameExtension(file.getPath()));

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
