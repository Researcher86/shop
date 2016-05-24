package com.tanat.shop.dao;

import com.tanat.shop.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Тестируем слой DAO товаров
 * Created by Tanat on 12.11.2015.
 */
public class GoodsDaoTest extends AbstractDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ClientDao clientDao;
    private Goods goods;

    @Before
    public void setUp() throws Exception {
        goods = Goods.createSimple();
    }

    @Test
    public void testSave() throws Exception {
        goodsDao.saveAndFlush(goods);

        assertNotNull(goodsDao.findOne(goods.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        goodsDao.saveAndFlush(goods);

        goodsDao.delete(goods);

        assertNull(goodsDao.findOne(goods.getId()));
    }

    @Test
    public void testFindLike() throws Exception {
        goodsDao.saveAndFlush(goods);

        assertNotNull(goodsDao.findByNameLike(goods.getName() + "%"));
    }

    @Test
    public void testAddComment() throws Exception {
        Client client = Client.createSimple();
        clientDao.saveAndFlush(client);

        goods.addComments(new Comment("Супер!", client));
        goods.addComments(new Comment("Супер!2", client));
        goodsDao.saveAndFlush(goods);

        List<Comment> comments = goodsDao.findOne(goods.getId()).getComments();

        assertFalse(comments.isEmpty());
        assertEquals(2, comments.size());

    }

    @Test
    public void testAddImage() throws Exception {
        Image image = Image.load("bumaga.png");
        goods.setImage(image);

        goodsDao.saveAndFlush(goods);

        Image storeImage = goodsDao.findOne(goods.getId()).getImage();
        assertEquals(image.getBase64(), storeImage.getBase64());
    }

    @Test
    public void testFindByCategory() throws Exception {
        Category category = new Category("Test");
        categoryDao.saveAndFlush(category);

        goods.setCategory(category);
        goodsDao.saveAndFlush(goods);

        List<Goods> byCategory = goodsDao.findByCategory(category);

        assertNotNull(byCategory);
        assertFalse(byCategory.isEmpty());
    }
}