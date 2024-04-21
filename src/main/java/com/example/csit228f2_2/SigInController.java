package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SigInController implements Serializable {

    public static boolean SignInSuccessful;
    public static String uname;
    public static int uid;

    @FXML
    private TextField tfusername;

    @FXML
    private TextField pfpassword;

    @FXML
    private void onRead(ActionEvent event) {
        String username = tfusername.getText();
        String password = pfpassword.getText();

        SignInSuccessful = signIn(username, password);

            if (SignInSuccessful) {
                System.out.println("Sign In Controller " + SignInSuccessful);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) tfusername.getScene().getWindow();
                    stage.setScene(new Scene(root));

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("error");
            }
        }

    private boolean signIn(String username, String password) {
        boolean status =false;

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                if(resultSet.getString("username").equals(username)){
                    if(resultSet.getString("password").equals(password)){
                        uname = username;
                        uid = resultSet.getInt("id");
                        status =  true;
                        System.out.println("sign in success");
                        System.out.println("status: " + status);
                        break;
                    }
                }
            }
            if(!status){
                System.out.println("Wrong credentials");
            }
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @FXML
    private void onRegister(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}