package com.tanat.shop.dao;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тестируем слой DAO корзины
 * Created by Tanat on 12.11.2015.
 */
public class CartDaoTest extends AbstractDaoTest {

    @Autowired
    private CartDao dao;

    @Autowired
    private ClientDao daoClient;

    @Autowired
    private GoodsDao daoGoods;
    private Client client;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        cart = new Cart(client);
    }

    @Test
    public void testSave() throws Exception {
        cart.setShippingAddress("Низнаю куда");

        daoClient.saveAndFlush(client);
        dao.saveAndFlush(cart);

        assertNotNull(dao.findOne(cart.getId()));
        assertNotNull(dao.findOne(cart.getId()).getClient());
    }

    @Test
    public void testAddOrder() throws Exception {
        final Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        cart.setShippingAddress("Низнаю куда");
        cart.addOrder(goods, 5);
        cart.addOrder(goods, 5);

        daoGoods.saveAndFlush(goods);
        daoClient.saveAndFlush(client);
        dao.saveAndFlush(cart);

        assertNotNull(dao.findOne(cart.getId()).getOrders());
        assertEquals(dao.findOne(cart.getId()).getOrders().size(), 2);
        assertEquals(dao.findOne(cart.getId()).getTotalPrice(), 50);
    }
}