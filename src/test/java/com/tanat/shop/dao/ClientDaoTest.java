package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


/**
 * Тестируем слой DAO клиентов
 * Created by Tanat on 12.11.2015.
 */
public class ClientDaoTest extends AbstractDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void testSave() throws Exception {
        Client client = Client.create();

        clientDao.saveAndFlush(client);

        assertNotNull(clientDao.findOne(client.getId()));
        assertEquals("Альпенов Танат Маратович", clientDao.findOne(client.getId()).getFio());
        assertEquals("87011520885", clientDao.findOne(client.getId()).getPhone());
        assertEquals("Дощанова 133б", clientDao.findOne(client.getId()).getAddress());
        assertEquals("researcher86@mail.ru", clientDao.findOne(client.getId()).getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        Client client = Client.create();

        clientDao.saveAndFlush(client);
        clientDao.delete(client);

        assertNull(clientDao.findOne(client.getId()));
    }
}