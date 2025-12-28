package com.rss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String url = "jdbc:mysql://localhost:3306/roomrental";
    private static final String uname = "root";
    private static final String pass = "Raja@9826";

    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(url,uname,pass);
            System.out.println("connection established");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
