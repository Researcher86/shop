package com.tanat.shop.repository;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Тестируем слой DAO категорий товаров
 * Created by Tanat on 12.11.2015.
 */
public class CategoryRepositoryTest extends AbstractRepositoryTest {

    private static final String CATEGORY_NAME = "Test";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GoodsRepository goodsRepository;
    private Category category;
    private Goods goods;

    @Before
    public void setUp() throws Exception {
        category = new Category(CATEGORY_NAME);
        categoryRepository.saveAndFlush(category);
        goods = Goods.createSimple();
        goods.setCategory(category);
    }

    @Test
    public void testSave() throws Exception {
        goodsRepository.saveAndFlush(goods);
        category.addGoods(goods);

        categoryRepository.saveAndFlush(category);

        final String categoryName = categoryRepository.findOne(category.getId()).getName();
        final String goodsCategoryName = categoryRepository.findOne(category.getId()).getGoodsList().get(0).getCategory().getName();

        assertEquals(CATEGORY_NAME, categoryName);
        assertEquals(1, categoryRepository.findOne(category.getId()).getGoodsList().size());
        assertEquals(categoryName, goodsCategoryName);
    }

    @Test
    public void testDelete() throws Exception {
        categoryRepository.saveAndFlush(category);
        categoryRepository.delete(category);

        assertNull(categoryRepository.findOne(category.getId()));
    }
}