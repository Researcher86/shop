package com.tanat.shop.domain.model;

import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Tanat on 02.10.2015.
 */
public class ShopServiceTest {

    @Test
    public void testClientByGoods() throws Exception {
        Client tanat = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б");
        Client janara = new Client("Альпенова Жанара Ериковна", "87015216343", "Дощанова 133б");

        Goods pencel = new Goods("Ручка", 5, "Обычная", "Обычная ручка", new byte[5]);
        Goods pencel2 = new Goods("Ручка2", 5, "Обычная", "Обычная ручка", new byte[5]);

        CategoryOfGoods categoryOfGoods = new CategoryOfGoods("Канцтовары");
        categoryOfGoods.addGoods(pencel);
        categoryOfGoods.addGoods(pencel2);

        for (Goods goods : categoryOfGoods.getGoods()) {
            System.out.println(goods.getName() + " - " + goods.getCategoryOfGoods().getName());
        }

        Comment tanatComment = new Comment("Супер!", pencel, tanat, Calendar.getInstance());
        Comment janaraComment = new Comment("Супер! Супер!", pencel2, janara, Calendar.getInstance());

        System.out.println(tanatComment.getClient().getFio() + " " + tanatComment.getGoods().getName() + " " + tanatComment.getText());
        System.out.println(janaraComment.getClient().getFio() + " " + janaraComment.getGoods().getName() + " " + janaraComment.getText());

        ShopService shopService = new ShopService();
        shopService.clientByGoods(tanat, pencel, 3);
        shopService.clientByGoods(tanat, pencel2, 2);
        shopService.clientByGoods(janara, pencel2, 2);

        Cart tanatCart = shopService.getCartByClient(tanat);
        for (Order order : tanatCart.getOrders()) {
            System.out.println("Name: " + order.getGoods().getName() +
                    ", count: " + order.getGoodsCount() +
                    ", price: " + order.getGoods().getPrice() +
                    ", sum: " + order.getTotalPrice());
        }
        System.out.println("Tanat cart price: " + tanatCart.getTotalPrice());

        Cart janaraCart = shopService.getCartByClient(janara);
        for (Order order : janaraCart.getOrders()) {
            System.out.println("Name: " + order.getGoods().getName() +
                    ", count: " + order.getGoodsCount() +
                    ", price: " + order.getGoods().getPrice() +
                    ", sum: " + order.getTotalPrice());
        }
        System.out.println("janara cart price: " + janaraCart.getTotalPrice());
    }
}