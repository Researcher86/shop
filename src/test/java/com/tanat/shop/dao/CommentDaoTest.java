package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
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
    private CommentDao commentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private GoodsDao goodsDao;

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
        clientDao.saveAndFlush(client);
        goodsDao.saveAndFlush(pencel);
        commentDao.saveAndFlush(comment1);

        assertNotNull(commentDao.findOne(comment1.getId()));
        assertNotNull(commentDao.findOne(comment1.getId()).getClient());
        assertEquals(commentDao.findOne(comment1.getId()).getClient().getFio(), "Альпенов Танат Маратович");
        assertNotNull(commentDao.findOne(comment1.getId()).getGoods());
        assertEquals(commentDao.findOne(comment1.getId()).getGoods().getName(), "Ручка");
        assertEquals(commentDao.findOne(comment1.getId()).getText(), "Супер!");
        assertNotNull(commentDao.findOne(comment1.getId()).getDate().getTime());
    }

    @Test
    public void testFindByGoods() throws Exception {
        clientDao.saveAndFlush(client);
        goodsDao.saveAndFlush(pencel);
        commentDao.saveAndFlush(comment1);
        commentDao.saveAndFlush(comment2);

        assertEquals(commentDao.findByGoods(pencel).size(), 2);

        assertNotNull(commentDao.findOne(comment1.getId()).getGoods());
        assertEquals(commentDao.findOne(comment1.getId()).getGoods().getName(), "Ручка");

        assertNotNull(commentDao.findOne(comment2.getId()).getGoods());
        assertEquals(commentDao.findOne(comment2.getId()).getGoods().getName(), "Ручка");
    }

    @Test
    public void testFindByClient() throws Exception {
        clientDao.saveAndFlush(client);
        goodsDao.saveAndFlush(pencel);
        commentDao.saveAndFlush(comment1);
        commentDao.saveAndFlush(comment2);

        assertEquals(commentDao.findByClient(client).size(), 2);

        assertNotNull(commentDao.findOne(comment1.getId()).getClient());
        assertEquals(commentDao.findOne(comment1.getId()).getClient().getFio(), "Альпенов Танат Маратович");

        assertNotNull(commentDao.findOne(comment2.getId()).getClient());
        assertEquals(commentDao.findOne(comment2.getId()).getClient().getFio(), "Альпенов Танат Маратович");
    }
}