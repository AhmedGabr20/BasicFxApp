package com.gabr.pos.db;


import java.sql.Connection;
import java.sql.DriverManager;

import static com.gabr.pos.Logging.logging.ERROR;
import static com.gabr.pos.Logging.logging.logException;
import static com.gabr.pos.Services.SettingService.*;

public class DbConnect {

    private static Connection connection;

    public static Connection getConnect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE_IP + "/" + DATABASE_NAME + "?sslmode=require", DATABASE_USER, DATABASE_PASS);
        } catch (Exception ex) {
            logException(ERROR,DbConnect.class.getName(),"getConnect",ex);
        }
        return connection;
    }

}

