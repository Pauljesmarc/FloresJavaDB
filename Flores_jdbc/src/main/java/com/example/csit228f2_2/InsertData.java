package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, email) VALUES (?, ?)")){

            String name = "paul";
            String email = "Paul@g.c";
            statement.setString(1,name);
            statement.setString(2, email);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Inserted Data Successfully!");
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
