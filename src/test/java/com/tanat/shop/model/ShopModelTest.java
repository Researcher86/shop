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

        assertEquals("Канцтовары", category.getName());
        assertEquals(2, category.getGoodsList().size());
        assertEquals(category, pencel.getCategory());
    }

    @Test
    public void testCommentForGoods() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        pencel.addComments(new Comment("Супер!", client));

        final Comment comment = pencel.getComments().get(0);
        assertEquals("Альпенов Танат Маратович", comment.getClient().getFio());
        assertEquals("Супер!", comment.getText());
        assertEquals(Calendar.getInstance().getTime(), comment.getDate().getTime());
    }

    @Test
    public void testCart() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        Cart cart = new Cart(client);
        cart.setShippingAddress("Низнаю куда");
        cart.addOrder(pencel, 2);

        assertEquals("Низнаю куда", cart.getShippingAddress());
        assertEquals(client, cart.getClient());
        assertEquals(10, cart.getTotalPrice());
    }

}