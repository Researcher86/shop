package com.tanat.shop.dao;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Tanat on 05.10.2015.
 */
public class CartDao extends AbstractDao {

    public Cart getById(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement("SELECT * FROM cart WHERE id = ?")) {
            stat.setInt(1, id);
            ResultSet result = stat.executeQuery();

            result.next();


            return null; /*result.getString("body");*/
        } catch (Exception e) {
            throw new DaoException("Cart.getById", e);
        }
    }

    public List<Cart> getByClient(Client client) {
        return null;
    }

    public void update(Cart cart) {

    }

    public void save(Cart cart) {

    }
}
