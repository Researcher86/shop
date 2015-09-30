package com.tanat.shop.domain;

import java.util.Map;

/**
 * Created by Tanat on 30.09.2015.
 */
public class ShopService {
    private Map<Client, Cart> clientCartMap;

    public void clientByGoods(Client client, Goods goods, int amount) {
        Cart cart = clientCartMap.get(client);

        if (cart == null) {
            cart = new Cart();
            clientCartMap.put(client, cart);
        }

        cart.addGoods(goods, amount);

    }
}
