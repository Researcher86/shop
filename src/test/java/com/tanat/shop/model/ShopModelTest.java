package com.tanat.shop.model;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


/**
 * Тестируем модель предметной области электронный магазин
 * Created by Tanat on 02.10.2015.
 */
public class ShopModelTest {

    @Test
    public void testCategoryOfGoods() throws Exception {
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        Category category = new Category("Канцтовары");
        category.addGoods(pencel);
        category.addGoods(pencel);

        assertEquals("Incorrect category name", category.getName(), "Канцтовары");
        assertEquals("Incorrect list goods", category.getGoodsList().size(), 2);
        assertEquals("Incorrect category in goods", pencel.getCategory(), category);
    }

    @Test
    public void testCommentForGoods() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        pencel.addComments(new Comment("Супер!", client));

        final Comment comment = pencel.getComments().get(0);
        assertEquals("Comment client is null", comment.getClient().getFio(), "Альпенов Танат Маратович");
        assertEquals("Comment incorrect text ", comment.getText(), "Супер!");
        assertEquals("Comment incorrect date", comment.getDate().getTime(), Calendar.getInstance().getTime());
    }

    @Test
    public void testCart() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        Cart cart = new Cart(client);
        cart.setShippingAddress("Низнаю куда");
        cart.addOrder(pencel, 2);

        assertEquals("Cart incorrect ShippingAddress", cart.getShippingAddress(), "Низнаю куда");
        assertEquals("Cart incorrect client", cart.getClient(), client);
        assertEquals("Cart TotalPrice", cart.getTotalPrice(), 10);
    }

}