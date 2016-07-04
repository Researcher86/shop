package com.tanat.shop.repository;

import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Client findByEmailAndPassword(String email, String password);
}
