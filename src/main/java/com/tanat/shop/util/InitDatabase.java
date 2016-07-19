package com.tanat.shop.util;

import com.tanat.shop.model.*;
import com.tanat.shop.repository.CategoryRepository;
import com.tanat.shop.repository.ClientRepository;
import com.tanat.shop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Компонент для иницыализации БД
 * Created by Tanat on 16.11.2015.
 */
@Component
public class InitDatabase {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostConstruct
    public void init() throws InterruptedException {
        Client client = new Client("Test", "Test", "Test", "test@test.com", "test");
        clientRepository.saveAndFlush(client);

        Category category2 = new Category("Ручка");
        categoryRepository.saveAndFlush(category2);

        Goods goods = new Goods("Ручка", 5, "Обычная", Image.load("pencel.jpg"));
        goods.setCategory(category2);
        goods.addComments(new Comment("Text comment", client));
        goods.addComments(new Comment("Text comment2", client));
        goodsRepository.saveAndFlush(goods);

        category2 = new Category("Дырокол");
        categoryRepository.saveAndFlush(category2);

        goods = new Goods("Дырокол", 45, "Обычный", Image.load("dirakol.jpg"));
        goods.setCategory(category2);
        goodsRepository.saveAndFlush(goods);


        category2 = new Category("Карандаш");
        categoryRepository.saveAndFlush(category2);

        goods = new Goods("Карандаш", 45, "Обычный", Image.load("karandash.jpg"));
        goods.setCategory(category2);
        goodsRepository.saveAndFlush(goods);


        category2 = new Category("Бумага");
        categoryRepository.saveAndFlush(category2);

        goods = new Goods("Бумага", 45, "Обычная", Image.load("bumaga.png"));
        goods.setCategory(category2);
        goodsRepository.saveAndFlush(goods);

        categoryRepository.saveAndFlush(new Category("Линейка"));
        categoryRepository.saveAndFlush(new Category("Папка"));
    }
}
