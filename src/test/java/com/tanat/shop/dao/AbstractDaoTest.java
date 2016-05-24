package com.tanat.shop.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Настройки для слоя DAO
 * Created by Tanat on 12.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/HSQL_DB.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractDaoTest {
}
