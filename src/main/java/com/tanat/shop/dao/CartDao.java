package com.tanat.shop.dao;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    List<Cart> findByClient(Client client);
}
