package com.tanat.shop.domain.model;

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
    public void testCategoryOfGoods() throws Exception {
        CategoryOfGoods categoryOfGoods = new CategoryOfGoods("Канцтовары");
        categoryOfGoods.addGoods(pencel);
        categoryOfGoods.addGoods(pencel2);

        for (Goods goods : categoryOfGoods.getGoods()) {
            assertNotNull("Goods is null", goods);
        }
    }

    @Test
    public void testCommentForGoods() throws Exception {
        Comment comment = new Comment("Супер!", pencel, tanat);

        assertNotNull("Comment client is null", comment.getClient());
        assertNotNull("Comment goods is null", comment.getGoods());
        assertNotNull("Comment text is null", comment.getText());
        assertNotNull("Comment date is null", comment.getDate());
    }
}