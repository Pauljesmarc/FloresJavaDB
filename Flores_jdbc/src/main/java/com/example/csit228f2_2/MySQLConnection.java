package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/floresjavadb";
    private static final String USERNAME = "paul";
    private static final String PASSWORD = "paul123";
    public static Connection getConnection(){
        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database");
            return connection;
        }catch (ClassNotFoundException | SQLException e) {
            e.getCause();
        }
        return null;
    }
}

