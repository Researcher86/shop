package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tanat on 12.11.2015.
 */
@Repository
public interface ClientDao extends JpaRepository<Client, Long> {

}
