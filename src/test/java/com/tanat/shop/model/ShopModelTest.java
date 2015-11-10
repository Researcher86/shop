package com.tanat.shop.model;

import org.junit.Test;

import java.util.Calendar;

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
        categoryOfGoods.addGoods(pencel);
        categoryOfGoods.addGoods(pencel);

        assertTrue("Incorrect category name", "Канцтовары".equals(categoryOfGoods.getName()));
        assertTrue("Incorrect list goods", categoryOfGoods.getGoodsList().size() == 2);

        for (Goods goods : categoryOfGoods.getGoodsList()) {
            assertNotNull("Goods is null", goods);
        }
    }

    @Test
    public void testCommentForGoods() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        Goods pencel = new Goods("Ручка", 5, "Обычная ручка");

        pencel.addComments(new Comment("Супер!", client));

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
        cart.addOrder(pencel, 2);

        assertTrue("Cart incorrect ShippingAddress", "Низнаю куда".equals(cart.getShippingAddress()));
        assertTrue("Cart incorrect client", cart.getClient() == client);
        assertTrue("Cart TotalPrice", cart.getTotalPrice() == 10);
    }

}