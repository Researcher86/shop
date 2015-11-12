package com.tanat.shop.dao;

import com.tanat.shop.model.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tanat on 12.11.2015.
 */
public class GoodsDaoTest extends AbstractDaoTest {

    @Autowired
    private GoodsDao dao;

    @Test
    @Rollback(false)
    public void testSave() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertTrue(dao.findAll().size() == 1);
    }

    @Test
    @Rollback(false)
    public void testGetById() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertNotNull(dao.getOne(goods.getId()));
    }

    @Test
    @Rollback(false)
    public void testFindLike() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertNotNull(dao.findByNameLike(goods.getName() + "%"));
    }
}