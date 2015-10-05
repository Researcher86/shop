package com.tanat.shop.service;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование сервиса эелектронного магазина
 * Created by Tanat on 05.10.2015.
 */
public class ShopServiceTest {

    private Client tanat;
    private Goods pencel;
    private Goods pencel2;

    @Before
    public void setUp() throws Exception {
        tanat = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б");
        pencel = new Goods("Ручка", 5, "Обычная", "Обычная ручка", new byte[5]);
        pencel2 = new Goods("Ручка2", 5, "Обычная", "Обычная ручка", new byte[5]);
    }

    @Test
    public void testClientByGoods() throws Exception {
        ShopService shopService = new ShopService();
        shopService.clientByGoods(tanat, pencel, 3);
        shopService.clientByGoods(tanat, pencel2, 2);

        Cart tanatCart = shopService.getCartByClient(tanat);
        for (Order order : tanatCart.getOrders()) {
            assertNotNull("Order is nul", order);
            assertNotNull("Goods is nul", order.getGoods());
            assertTrue("GoodsCount is 0", order.getGoodsCount() > 0);
            assertTrue("Goods price is 0", order.getGoods().getPrice() > 0);
            assertTrue("Order total price is 0", order.getTotalPrice() > 0);
        }

        assertTrue("Cart total price error", tanatCart.getTotalPrice() == 25);
    }

    @Test
    public void testGetCartByClient() throws Exception {
        ShopService shopService = new ShopService();
        shopService.clientByGoods(tanat, pencel, 3);
        shopService.clientByGoods(tanat, pencel2, 2);

        assertNotNull("Cart is nul", shopService.getCartByClient(tanat));
    }
}