package com.tanat.shop.service;

import com.tanat.shop.dao.ClientDao;
import com.tanat.shop.model.Client;

/**
 * Created by Tanat on 05.10.2015.
 */
public class UserService {
    private ClientDao clientDao = new ClientDao();

    public void registration(String fio, String phone, String address, String email) {
        Client client = new Client(fio, phone, address, email);
        clientDao.save(client);
    }

    public void delete(Client client) {
        clientDao.delete(client);
    }

    public Client findByFio(String fio) {
        return clientDao.findByFio(fio);
    }

    public Client findByPhone(String phone) {
        return clientDao.findByPhone(phone);
    }

    public Client findByAddress(String address) {
        return clientDao.findByAddress(address);
    }

    public Client findByEmail(String email) {
        return clientDao.findByEmail(email);
    }


}
