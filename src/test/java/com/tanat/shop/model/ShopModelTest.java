package com.tanat.shop.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Тестирование модели электронного магазина
 * Created by Tanat on 02.10.2015.
 */
public class ShopModelTest {

    private Client tanat;
    private Goods pencel;
    private Goods pencel2;

    @Before
    public void setUp() throws Exception {
        tanat = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");
        pencel = new Goods("Ручка", 5, "Обычная", "Обычная ручка", new byte[5]);
        pencel2 = new Goods("Ручка2", 5, "Обычная", "Обычная ручка", new byte[5]);
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");

        assertTrue("Incorrect client fio", "Альпенов Танат Маратович".equals(client.getFio()));
        assertTrue("Incorrect client phone", "87011520885".equals(client.getPhone()));
        assertTrue("Incorrect client address", "Дощанова 133б".equals(client.getAddress()));
        assertTrue("Incorrect client email", "researcher86@mail.ru".equals(client.getEmail()));
    }

    @Test
    public void testCreateGoods() throws Exception {
        Goods goods = new Goods("Ручка", 5, "Обычная", "Обычная ручка", new byte[5]);

        assertTrue("Incorrect goods name", "Ручка".equals(goods.getName()));
        assertTrue("Incorrect goods price", goods.getPrice() == 5);
        assertTrue("Incorrect goods ShortDescription", "Обычная".equals(goods.getShortDescription()));
        assertTrue("Incorrect goods FullDescription", "Обычная ручка".equals(goods.getFullDescription()));
        assertTrue("Incorrect goods picture", goods.getPicture().length == 5);
    }

    @Test
    public void testCreateCategoryOfGoods() throws Exception {
        CategoryOfGoods categoryOfGoods = new CategoryOfGoods("Канцтовары");
        categoryOfGoods.addGoods(pencel);
        categoryOfGoods.addGoods(pencel2);

        assertTrue("Incorrect category name", "Канцтовары".equals(categoryOfGoods.getName()));
        assertTrue("Incorrect list goods", categoryOfGoods.getGoods().size() == 2);

        for (Goods goods : categoryOfGoods.getGoods()) {
            assertNotNull("Goods is null", goods);
            assertTrue("Goods incorrect categoryOfGoods", goods.getCategoryOfGoods() == categoryOfGoods);
        }
    }

    @Test
    public void testCreateCommentForGoods() throws Exception {
        Comment comment = new Comment("Супер!", tanat);
        comment.setGoods(pencel);

        assertNotNull("Comment client is null", comment.getClient());
        assertNotNull("Comment goods is null", comment.getGoods());
        assertNotNull("Comment text is null", comment.getText());
        assertNotNull("Comment date is null", comment.getDate());
    }

    @Test
    public void testCreateCart() throws Exception {
        Cart cart = new Cart(tanat, "Низнаю куда");
        cart.addGoods(pencel, 2);

        assertTrue("Cart incorrect ShippingAddress", "Низнаю куда".equals(cart.getShippingAddress()));
        assertTrue("Cart incorrect client", cart.getClient() == tanat);
        assertTrue("Cart order is empty", cart.getOrders().size() == 1);
        assertTrue("Cart TotalPrice", cart.getTotalPrice() == 10);
    }

}