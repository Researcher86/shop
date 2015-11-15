package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Тестируем слой DAO комментариев
 * Created by Tanat on 12.11.2015.
 */
public class CommentDaoTest extends AbstractDaoTest {

    @Autowired
    private CommentDao dao;

    @Autowired
    private ClientDao daoClient;

    @Autowired
    private GoodsDao daoGoods;

    private Client client;
    private Goods pencel;
    private Comment comment1, comment2;

    @Before
    public void setUp() throws Exception {
        client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        pencel = new Goods("Ручка", 5, "Обычная ручка");
        comment1 = new Comment("Супер!", client);
        comment2 = new Comment("Супер!2", client);
        comment1.setGoods(pencel);
        comment2.setGoods(pencel);
    }

    @Test
    public void testSave() throws Exception {
        daoClient.saveAndFlush(client);
        daoGoods.saveAndFlush(pencel);
        dao.saveAndFlush(comment1);

        assertNotNull(dao.findOne(comment1.getId()));
        assertNotNull(dao.findOne(comment1.getId()).getClient());
        assertEquals(dao.findOne(comment1.getId()).getClient().getFio(), "Альпенов Танат Маратович");
        assertNotNull(dao.findOne(comment1.getId()).getGoods());
        assertEquals(dao.findOne(comment1.getId()).getGoods().getName(), "Ручка");
        assertEquals(dao.findOne(comment1.getId()).getText(), "Супер!");
        assertNotNull(dao.findOne(comment1.getId()).getDate().getTime());
    }

    @Test
    public void testFindByGoods() throws Exception {
        daoClient.saveAndFlush(client);
        daoGoods.saveAndFlush(pencel);
        dao.saveAndFlush(comment1);
        dao.saveAndFlush(comment2);

        assertEquals(dao.findByGoods(pencel).size(), 2);

        assertNotNull(dao.findOne(comment1.getId()).getGoods());
        assertEquals(dao.findOne(comment1.getId()).getGoods().getName(), "Ручка");

        assertNotNull(dao.findOne(comment2.getId()).getGoods());
        assertEquals(dao.findOne(comment2.getId()).getGoods().getName(), "Ручка");
    }

    @Test
    public void testFindByClient() throws Exception {
        daoClient.saveAndFlush(client);
        daoGoods.saveAndFlush(pencel);
        dao.saveAndFlush(comment1);
        dao.saveAndFlush(comment2);

        assertEquals(dao.findByClient(client).size(), 2);

        assertNotNull(dao.findOne(comment1.getId()).getClient());
        assertEquals(dao.findOne(comment1.getId()).getClient().getFio(), "Альпенов Танат Маратович");

        assertNotNull(dao.findOne(comment2.getId()).getClient());
        assertEquals(dao.findOne(comment2.getId()).getClient().getFio(), "Альпенов Танат Маратович");
    }
}