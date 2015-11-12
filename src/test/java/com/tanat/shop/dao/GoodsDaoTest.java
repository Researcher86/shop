package com.tanat.shop.dao;

import com.tanat.shop.model.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 12.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/database.xml"
})
public class GoodsDaoTest {

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