package com.tanat.shop.repository;

import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Client findByEmailAndPassword(String email, String password);
}
