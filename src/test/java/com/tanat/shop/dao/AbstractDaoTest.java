package com.tanat.shop.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Tanat on 12.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/database.xml"
})
public abstract class AbstractDaoTest {
}