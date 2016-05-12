package com.tanat.shop.dao;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Тестируем слой DAO категорий товаров
 * Created by Tanat on 12.11.2015.
 */
public class CategoryDaoTest extends AbstractDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GoodsDao goodsDao;

    @Test
    @Transactional
    public void testSave() throws Exception {
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка", null);
        Category category = new Category("Канцтовары");
        category.addGoods(pencel);

        categoryDao.saveAndFlush(category);
        goodsDao.saveAndFlush(pencel);

        final String categoryName = categoryDao.findOne(category.getId()).getName();
        final String goodsCategoryName = categoryDao.findOne(category.getId()).getGoodsList().get(0).getCategory().getName();

        assertEquals("Канцтовары", categoryName);
        assertEquals(1, categoryDao.findOne(category.getId()).getGoodsList().size());
        assertEquals(categoryName, goodsCategoryName);
    }

    @Test
    public void testDelete() throws Exception {
        Category category = new Category("Канцтовары");

        categoryDao.saveAndFlush(category);
        categoryDao.delete(category);

        assertNull(categoryDao.findOne(category.getId()));
    }

    @Test(expected = Exception.class)
    public void testDeleteWithGoods() throws Exception {
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка", null);
        Category category = new Category("Канцтовары");
        category.addGoods(pencel);

        categoryDao.saveAndFlush(category);
        goodsDao.saveAndFlush(pencel);
        categoryDao.delete(category);

        fail();
    }
}