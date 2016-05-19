package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {

}
