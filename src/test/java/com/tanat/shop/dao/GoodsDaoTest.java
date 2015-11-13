package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tanat on 12.11.2015.
 */
public class GoodsDaoTest extends AbstractDaoTest {

    @Autowired
    private GoodsDao dao;

    @Autowired
    private ClientDao daoClient;

    @Test
    public void testSave() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertTrue(dao.findAll().size() == 1);
    }

    @Test
    public void testGetById() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertNotNull(dao.getOne(goods.getId()));
    }

    @Test
    public void testFindLike() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        dao.saveAndFlush(goods);

        assertNotNull(dao.findByNameLike(goods.getName() + "%"));
    }

    @Test
    @Transactional
    public void testAddComment() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        daoClient.saveAndFlush(client);

        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        pencel.addComments(new Comment("Супер!", client));
        pencel.addComments(new Comment("Супер!2", client));
        dao.saveAndFlush(pencel);

        Comment comment = dao.findOne(pencel.getId()).getComments().get(0);
        Comment comment2 = dao.findOne(pencel.getId()).getComments().get(1);

        assertTrue("Супер!".equals(comment.getText()));
        assertTrue("Ручка".equals(comment.getGoods().getName()));
        assertTrue("Альпенов Танат Маратович".equals(comment.getClient().getFio()));

        assertTrue("Супер!2".equals(comment2.getText()));
        assertTrue("Ручка".equals(comment2.getGoods().getName()));
        assertTrue("Альпенов Танат Маратович".equals(comment2.getClient().getFio()));
    }
}