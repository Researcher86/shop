package com.tanat.shop.dao;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Тестируем слой DAO категорий товаров
 * Created by Tanat on 12.11.2015.
 */
public class CategoryDaoTest extends AbstractDaoTest {

    public static final String CATEGORY_NAME = "Канцтовары";

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GoodsDao goodsDao;
    private Category category;
    private Goods goods;

    @Before
    public void setUp() throws Exception {
        category = new Category(CATEGORY_NAME);
        goods = Goods.createSimple();
    }

    @Test
    public void testSave() throws Exception {
        goodsDao.saveAndFlush(goods);
        category.addGoods(goods);

        categoryDao.saveAndFlush(category);

        final String categoryName = categoryDao.findOne(category.getId()).getName();
        final String goodsCategoryName = categoryDao.findOne(category.getId()).getGoodsList().get(0).getCategory().getName();

        assertEquals(CATEGORY_NAME, categoryName);
        assertEquals(1, categoryDao.findOne(category.getId()).getGoodsList().size());
        assertEquals(categoryName, goodsCategoryName);
    }

    @Test
    public void testDelete() throws Exception {
        categoryDao.saveAndFlush(category);
        categoryDao.delete(category);

        assertNull(categoryDao.findOne(category.getId()));
    }

    @Test(expected = Exception.class)
    public void testDeleteWithGoods() throws Exception {
        category.addGoods(goods);

        goodsDao.saveAndFlush(goods);
        categoryDao.saveAndFlush(category);
        categoryDao.delete(category);

        fail();
    }
}