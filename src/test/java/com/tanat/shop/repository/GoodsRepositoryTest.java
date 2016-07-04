package com.tanat.shop.repository;

import com.tanat.shop.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Тестируем слой DAO товаров
 * Created by Tanat on 12.11.2015.
 */
public class GoodsRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;
    private Goods goods;

    @Before
    public void setUp() throws Exception {
        goods = Goods.createSimple();
    }

    @Test
    public void testSave() throws Exception {
        goodsRepository.saveAndFlush(goods);

        assertNotNull(goodsRepository.findOne(goods.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        goodsRepository.saveAndFlush(goods);

        goodsRepository.delete(goods);

        assertNull(goodsRepository.findOne(goods.getId()));
    }

    @Test
    public void testFindLike() throws Exception {
        goodsRepository.saveAndFlush(goods);

        assertNotNull(goodsRepository.findByNameLike(goods.getName() + "%"));
    }

    @Test
    public void testAddComment() throws Exception {
        Client client = Client.createSimple();
        clientRepository.saveAndFlush(client);

        goods.addComments(new Comment("Супер!", client));
        goods.addComments(new Comment("Супер!2", client));
        goodsRepository.saveAndFlush(goods);

        List<Comment> comments = goodsRepository.findOne(goods.getId()).getComments();

        assertFalse(comments.isEmpty());
        assertEquals(2, comments.size());

    }

    @Test
    public void testAddImage() throws Exception {
        Image image = Image.load("bumaga.png");
        goods.setImage(image);

        goodsRepository.saveAndFlush(goods);

        Image storeImage = goodsRepository.findOne(goods.getId()).getImage();
        assertEquals(image.getBase64(), storeImage.getBase64());
    }

    @Test
    public void testFindByCategory() throws Exception {
        Category category = new Category("Test");
        categoryRepository.saveAndFlush(category);

        goods.setCategory(category);
        goodsRepository.saveAndFlush(goods);

        List<Goods> byCategory = goodsRepository.findByCategory(category);

        assertNotNull(byCategory);
        assertFalse(byCategory.isEmpty());
    }
}