package com.tanat.shop.repository;

import com.tanat.shop.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


/**
 * Тестируем слой DAO клиентов
 * Created by Tanat on 12.11.2015.
 */
public class ClientRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = Client.createSimple();
    }

    @Test
    public void testSave() throws Exception {
        clientRepository.saveAndFlush(client);

        Client storeClient = clientRepository.findOne(client.getId());

//        assertSame(client, storeClient); //TODO: Научится сравнивать объекты

        assertNotNull(storeClient);
        assertSame(client.getFio(), storeClient.getFio());
        assertEquals(client.getPhone(), storeClient.getPhone());
        assertEquals(client.getAddress(), storeClient.getAddress());
        assertEquals(client.getEmail(), storeClient.getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        clientRepository.saveAndFlush(client);

        clientRepository.delete(client);

        Client storeClient = clientRepository.findOne(client.getId());
        assertNull(storeClient);
    }

    @Test
    public void testFindByEmail() throws Exception {
        clientRepository.saveAndFlush(client);

        Client storeClient = clientRepository.findByEmail(client.getEmail());

        assertNotNull(storeClient);
    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {
        clientRepository.saveAndFlush(client);

        Client storeClient = clientRepository.findByEmailAndPassword(client.getEmail(), client.getPassword());

        assertNotNull(storeClient);
    }
}