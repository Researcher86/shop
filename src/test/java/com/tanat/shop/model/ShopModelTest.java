package com.tanat.shop.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Тестирование модели электронного магазина
 * Created by Tanat on 02.10.2015.
 */
public class ShopModelTest {

    @Test
    public void testClient() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");

        assertTrue("Incorrect client fio", "Альпенов Танат Маратович".equals(client.getFio()));
        assertTrue("Incorrect client phone", "87011520885".equals(client.getPhone()));
        assertTrue("Incorrect client address", "Дощанова 133б".equals(client.getAddress()));
        assertTrue("Incorrect client email", "researcher86@mail.ru".equals(client.getEmail()));
    }

    @Test
    public void testGoods() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная ручка");

        assertTrue("Incorrect goods name", "Ручка".equals(goods.getName()));
        assertTrue("Incorrect goods price", goods.getPrice() == 5);
        assertTrue("Incorrect goods description", "Обычная ручка".equals(goods.getDescription()));
    }

    @Test
    public void testCategoryOfGoods() throws Exception {
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        CategoryOfGoods categoryOfGoods = new CategoryOfGoods("Канцтовары");
        categoryOfGoods.getGoods().add(pencel);
        categoryOfGoods.getGoods().add(pencel);

        assertTrue("Incorrect category name", "Канцтовары".equals(categoryOfGoods.getName()));
        assertTrue("Incorrect list goods", categoryOfGoods.getGoods().size() == 2);

        for (Goods goods : categoryOfGoods.getGoods()) {
            assertNotNull("Goods is null", goods);
        }
    }

    @Test
    public void testCommentForGoods() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        pencel.getComments().add(new Comment("Супер!", client));

        final Comment comment = pencel.getComments().get(0);
        assertTrue("Comment client is null", "Альпенов Танат Маратович".equals(comment.getClient().getFio()));
        assertTrue("Comment incorrect text ", "Супер!".equals(comment.getText()));
        assertTrue("Comment incorrect date", Calendar.getInstance().getTime().toString().equals(comment.getDate().getTime().toString()));
    }

    @Test
    public void testCart() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        Cart cart = new Cart(client, "Низнаю куда");
        cart.getOrders().add(new Order(pencel, 2));

        assertTrue("Cart incorrect ShippingAddress", "Низнаю куда".equals(cart.getShippingAddress()));
        assertTrue("Cart incorrect client", cart.getClient() == client);
        assertTrue("Cart order is empty", cart.getOrders().size() == 1);
        assertTrue("Cart TotalPrice", cart.getTotalPrice() == 10);
    }

}