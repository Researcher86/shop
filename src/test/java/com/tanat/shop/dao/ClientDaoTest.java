package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import org.junit.Before;
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
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = Client.createSimple();
    }

    @Test
    public void testSave() throws Exception {
        clientDao.saveAndFlush(client);

        Client storeClient = clientDao.findOne(client.getId());

//        assertSame(client, storeClient); //TODO: Научится сравнивать объекты

        assertNotNull(storeClient);
        assertSame(client.getFio(), storeClient.getFio());
        assertEquals(client.getPhone(), storeClient.getPhone());
        assertEquals(client.getAddress(), storeClient.getAddress());
        assertEquals(client.getEmail(), storeClient.getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        clientDao.saveAndFlush(client);

        clientDao.delete(client);

        Client storeClient = clientDao.findOne(client.getId());
        assertNull(storeClient);
    }

    @Test
    public void testFindByEmail() throws Exception {
        clientDao.saveAndFlush(client);

        Client storeClient = clientDao.findByEmail(client.getEmail());

        assertNotNull(storeClient);
    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {
        clientDao.saveAndFlush(client);

        Client storeClient = clientDao.findByEmailAndPassword(client.getEmail(), client.getPassword());

        assertNotNull(storeClient);
    }
}