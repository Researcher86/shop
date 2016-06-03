package com.tanat.shop.service;

import com.tanat.shop.dao.ClientDao;
import com.tanat.shop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Client save(Client client) {
        return clientDao.saveAndFlush(client);
    }

    public Client findByEmail(String email) {
        return clientDao.findByEmail(email);
    }

    public Client findByEmailAndPassword(String email, String password) {
        return clientDao.findByEmailAndPassword(email, password);
    }
}
