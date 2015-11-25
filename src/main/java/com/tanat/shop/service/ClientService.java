package com.tanat.shop.service;

import com.tanat.shop.dao.ClientDao;
import com.tanat.shop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanat on 25.11.2015.
 */
@Service
public class ClientService {
    @Autowired
    private ClientDao clientDao;

    public List<Client> getAll() {
        return clientDao.findAll();
    }

    public Client getById(Long id) {
        return clientDao.findOne(id);
    }

    public void save(Client client) {
        clientDao.saveAndFlush(client);
    }

}
