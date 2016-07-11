package com.tanat.shop.service;

import com.tanat.shop.model.Client;
import com.tanat.shop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        return clientRepository.findOne(id);
    }

    public Client save(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Client findByEmailAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }
}
