package com.tanat.shop.repository;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByClient(Client client);
}
