package com.tanat.shop.repository;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Тестируем слой DAO корзины
 * Created by Tanat on 12.11.2015.
 */
public class CartRepositoryTest extends AbstractRepositoryTest {

    public static final String SHIPPING_ADDRESS = "Не знаю куда";
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GoodsRepository goodsRepository;
    private Client client;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        client = Client.createSimple();
        cart = new Cart(client);
        clientRepository.saveAndFlush(client);
    }

    @Test
    public void testSave() throws Exception {
        cart.setShippingAddress(SHIPPING_ADDRESS);

        cartRepository.saveAndFlush(cart);

        assertNotNull(cartRepository.findOne(cart.getId()));
        assertNotNull(cartRepository.findOne(cart.getId()).getClient());
    }

    @Test
    public void testDelete() throws Exception {
        cart.setShippingAddress(SHIPPING_ADDRESS);

        cartRepository.saveAndFlush(cart);
        cartRepository.delete(cart);

        assertNull(cartRepository.findOne(cart.getId()));
        assertNotNull(clientRepository.findOne(client.getId()));
    }

    @Test
    public void testAddOrder() throws Exception {
        final Goods goods = Goods.createSimple();

        cart.setShippingAddress(SHIPPING_ADDRESS);
        cart.addOrder(goods, 5);
        cart.addOrder(goods, 5);

        goodsRepository.saveAndFlush(goods);
        cartRepository.saveAndFlush(cart);

        assertNotNull(cartRepository.findOne(cart.getId()).getOrders());
        assertEquals(1, cartRepository.findOne(cart.getId()).getOrders().size());
        assertEquals(50, cartRepository.findOne(cart.getId()).getTotalPrice());
    }

    @Test
    public void testFindByClient() throws Exception {
        cart.setShippingAddress(SHIPPING_ADDRESS);

        cartRepository.saveAndFlush(cart);

        assertNotNull(cartRepository.findByClient(client));
    }
}