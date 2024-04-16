package com.example.csit228f2_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {

    @FXML
    private TextField tfusername;

    @FXML
    private TextField pfpassword;

    @FXML
    private TextField tfemail;
    @FXML
    private TextField ntfusername;

    @FXML
    private TextField npfpassword;

    @FXML
    private TextField ntfemail;

    @FXML
    private void onRead() {
        String username = tfusername.getText();
        String password = pfpassword.getText();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                if(resultSet.getString("username").equals(username)){
                    if(resultSet.getString("password").equals(password)){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    }else{
                        System.out.println("Incorrect Passwornd");
                    }
                }else{
                    System.out.println("Incorrect Username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCreate() {
        String username = tfusername.getText();
        String password = pfpassword.getText();
        String email = tfemail.getText();

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (username, password,email) VALUES (?, ?,?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDelete(){
        String username = tfusername.getText();

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                	                     "DELETE FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            int rowsDeleted = preparedStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Data deleted successfully!");
                        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onUpdate(){

        try (Connection connection = MySQLConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE users SET username = ? , password = ? , email = ? WHERE username = ? ")) {

                            String userToBeUpdate = tfusername.getText();
            	            String newUsername = ntfusername.getText();
                            String newPassword = npfpassword.getText();
            	            String newEmail = ntfemail.getText();


            	            preparedStatement.setString(1, newUsername);
            	            preparedStatement.setString(2, newPassword);
            	            preparedStatement.setString(3, newEmail);
                            preparedStatement.setString(4,userToBeUpdate);

            	            int rowsUpdated = preparedStatement.executeUpdate();
            	            if (rowsUpdated > 0) {
                                System.out.println("Data updated successfully!");
                            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}