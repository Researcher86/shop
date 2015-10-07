package com.tanat.shop.dao;

import java.sql.*;

/**
 * Created by Tanat on 05.10.2015.
 */
public class AbstractDao {
    private String URL = "jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=UTF-8";
    private String host = "localhost";
    private String dataBase = "shop";
    private String user = "root";
    private String password = "";

    protected Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance(); // загружаем драйвер
        return DriverManager.getConnection(String.format(URL, host, dataBase), user, password);// задаём строку подключения
    }

}
