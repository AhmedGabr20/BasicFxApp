package com.gabr.pos.db;


import java.sql.Connection;
import java.sql.DriverManager;

import static com.gabr.pos.Services.SettingService.*;

public class DbConnect {

    // static final String jdbcUrl = "jdbc:mysql://192.168.1.31:3306/elnoor_v2?useSSL=false";
    static final String jdbcUrl = "jdbc:mysql://localhost/market?useSSL=false";
    //static final String jdbcUrl = "jdbc:mysql://192.168.1.2/elnoor_v2?useSSL=false";


    private static Connection connection;

    public static Connection getConnect() {

        try {

        //    connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?useSSL=false&allowPublicKeyRetrieval=True", user, pass);
            connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE_IP + "/" + DATABASE_NAME + "?sslmode=require", DATABASE_USER, DATABASE_PASS);
        } catch (Exception ex) {
            String param = "#ip : " + DATABASE_IP + "  #db : " + DATABASE_NAME + " #user : " + DATABASE_USER + "  #pass : " + DATABASE_PASS;
        //    LOG_EXCEP_PARAM("DbConnect", "getConnect", ex, param);
        }
        return connection;
    }

}

