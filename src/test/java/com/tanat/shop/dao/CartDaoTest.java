package com.tanat.shop.dao;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Тестируем слой DAO корзины
 * Created by Tanat on 12.11.2015.
 */
public class CartDaoTest extends AbstractDaoTest {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private GoodsDao goodsDao;
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

        clientDao.saveAndFlush(client);
        cartDao.saveAndFlush(cart);

        assertNotNull(cartDao.findOne(cart.getId()));
        assertNotNull(cartDao.findOne(cart.getId()).getClient());
    }

    @Test
    public void testDelete() throws Exception {
        cart.setShippingAddress("Низнаю куда");

        clientDao.saveAndFlush(client);
        cartDao.saveAndFlush(cart);
        cartDao.delete(cart);

        assertNull(cartDao.findOne(cart.getId()));
        assertNotNull(clientDao.findOne(client.getId()));
    }

    @Test
    public void testAddOrder() throws Exception {
        final Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        cart.setShippingAddress("Низнаю куда");
        cart.addOrder(goods, 5);
        cart.addOrder(goods, 5);

        goodsDao.saveAndFlush(goods);
        clientDao.saveAndFlush(client);
        cartDao.saveAndFlush(cart);

        assertNotNull(cartDao.findOne(cart.getId()).getOrders());
        assertEquals(2, cartDao.findOne(cart.getId()).getOrders().size());
        assertEquals(50, cartDao.findOne(cart.getId()).getTotalPrice());
    }

    @Test
    public void testFindByClient() throws Exception {
        cart.setShippingAddress("Низнаю куда");

        clientDao.saveAndFlush(client);
        cartDao.saveAndFlush(cart);

        assertNotNull(cartDao.findByClient(client));
    }
}