package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Тестируем слой DAO клиентов
 * Created by Tanat on 12.11.2015.
 */
public class ClientDaoTest extends AbstractDaoTest {

    @Autowired
    private ClientDao dao;

    @Test
    public void testSave() throws Exception {
        Client client = new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru");

        dao.saveAndFlush(client);

        assertNotNull(dao.findOne(client.getId()));
        assertEquals(dao.findOne(client.getId()).getFio(), "Альпенов Танат Маратович");
        assertEquals(dao.findOne(client.getId()).getPhone(), "87011520885");
        assertEquals(dao.findOne(client.getId()).getAddress(), "Дощанова 133б");
        assertEquals(dao.findOne(client.getId()).getEmail(), "researcher86@mail.ru");
    }
}