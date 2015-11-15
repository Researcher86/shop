package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Тестируем слой DAO товаров
 * Created by Tanat on 12.11.2015.
 */
public class GoodsDaoTest extends AbstractDaoTest {

    @Autowired
    private GoodsDao dao;

    @Autowired
    private ClientDao daoClient;
    private Goods goods;

    @Before
    public void setUp() throws Exception {
        goods = new Goods("Ручка", 5, "Обычная ручка");
    }

    @Test
    public void testSave() throws Exception {
        dao.saveAndFlush(goods);

        assertNotNull(dao.findOne(goods.getId()));
    }

    @Test
    public void testFindLike() throws Exception {
        dao.saveAndFlush(goods);

        assertNotNull(dao.findByNameLike(goods.getName() + "%"));
    }

    @Test
    @Transactional
    public void testAddComment() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        daoClient.saveAndFlush(client);

        goods.addComments(new Comment("Супер!", client));
        goods.addComments(new Comment("Супер!2", client));
        dao.saveAndFlush(goods);

        Comment comment = dao.findOne(goods.getId()).getComments().get(0);
        Comment comment2 = dao.findOne(goods.getId()).getComments().get(1);

        assertEquals(comment.getText(), "Супер!");
        assertEquals(comment.getGoods().getName(), "Ручка");
        assertEquals(comment.getClient().getFio(), "Альпенов Танат Маратович");

        assertEquals(comment2.getText(), "Супер!2");
        assertEquals(comment2.getGoods().getName(), "Ручка");
        assertEquals(comment2.getClient().getFio(), "Альпенов Танат Маратович");
    }
}