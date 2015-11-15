package com.tanat.shop.dao;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Тестируем слой DAO категорий товаров
 * Created by Tanat on 12.11.2015.
 */
public class CategoryDaoTest extends AbstractDaoTest {

    @Autowired
    private CategoryDao dao;

    @Autowired
    private GoodsDao daoGoods;

    @Test
    @Transactional
    public void testSave() throws Exception {
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");
        Category category = new Category("Канцтовары");
        category.addGoods(pencel);

        dao.saveAndFlush(category);
        daoGoods.saveAndFlush(pencel);

        final String categoryName = dao.findOne(category.getId()).getName();
        final String goodsCategoryName = dao.findOne(category.getId()).getGoodsList().get(0).getCategory().getName();

        assertEquals("Incorrect category name", categoryName, "Канцтовары");
        assertEquals("Incorrect list goods", dao.findOne(category.getId()).getGoodsList().size(), 1);
        assertEquals("Incorrect category in goods", goodsCategoryName, categoryName);
    }
}