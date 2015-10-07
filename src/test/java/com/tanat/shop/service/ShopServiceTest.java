package com.tanat.shop.service;

import com.tanat.shop.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Тестирование сервиса эелектронного магазина
 * Created by Tanat on 05.10.2015.
 */
public class ShopServiceTest {

    private ShopService shopService;
    private Client tanat;
    private Goods pencel;
    private Goods pencel2;

    @Before
    public void setUp() throws Exception {
        shopService = new ShopService();
        tanat = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        pencel = new Goods("Ручка", 5, "Обычная", "Обычная ручка", new byte[5]);
        pencel2 = new Goods("Ручка2", 5, "Обычная", "Обычная ручка", new byte[5]);
    }

    @Test
    public void testNewCart() throws Exception {
        Cart cart = shopService.newCart();
        assertNotNull("Not create cart", cart);
    }

    @Test
    public void testNewCategoryOfGoods() throws Exception {
        CategoryOfGoods categoryOfGoods = shopService.newCategoryOfGoods("Канцтовары");
        assertNotNull("Not create categoryOfGoods", categoryOfGoods);
    }

    @Test
    public void testNewComment() throws Exception {
        Comment comment = shopService.newComment("Test", tanat);
        assertNotNull("Not create comment for goods", comment);
    }

    @Test
    public void testAddCommentForGoods() throws Exception {
        Comment comment = shopService.newComment("Test", tanat);

        shopService.addCommentForGoods(comment, pencel);

        assertTrue("Incorrect goods in comment", comment.getGoods() == pencel);
    }

    @Test
    public void testAddGoodsInCategory() throws Exception {
        CategoryOfGoods categoryOfGoods = shopService.newCategoryOfGoods("Test");

        shopService.addGoodsInCategory(pencel, categoryOfGoods);

        assertTrue("Incorrect add GoodsInCategory", categoryOfGoods.getGoods().contains(pencel));
        assertTrue("Incorrect add GoodsInCategory", pencel.getCategoryOfGoods() == categoryOfGoods);
    }

    @Test
    public void testCartAddGoods() throws Exception {
        Cart tanatCart = new Cart(tanat, "Низнаю куда");

        shopService.cartAddGoods(tanatCart, pencel, 3);
        shopService.cartAddGoods(tanatCart, pencel2, 2);

        for (Order order : tanatCart.getOrders()) {
            assertNotNull("Order is nul", order);
            assertNotNull("Goods is nul", order.getGoods());
            assertTrue("GoodsCount is 0", order.getGoodsCount() > 0);
            assertTrue("Goods price is 0", order.getGoods().getPrice() > 0);
            assertTrue("Order total price is 0", order.getTotalPrice() > 0);
        }

        assertTrue("Cart total price error", tanatCart.getTotalPrice() == 25);
    }
}