package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void createtable(){
        try{
            Connection connection = MySQLConnection.getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.addBatch("CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255),"
                    + "password VARCHAR(255) NOT NULL)");

            statement.addBatch("CREATE TABLE IF NOT EXISTS tbltask ("
                    + "taskID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "userID INT NOT NULL,"
                    + "task VARCHAR(100) NOT NULL,"
                    + "FOREIGN KEY (userId) REFERENCES users(id)"
                    + ")");
            connection.commit();
            statement.executeBatch();
            statement.close();
            connection.close();


            System.out.println("Created two tables");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fail to create tables");
        }
    }
}