package com.example.csit228f2_2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField pfpassword;
    @FXML
    private TextField tfemail;
    @FXML
    private AnchorPane createpane;

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
                Stage stage = (Stage) createpane.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("Data inserted Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
