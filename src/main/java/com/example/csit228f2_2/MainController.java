package com.example.csit228f2_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;
import java.sql.*;

import javafx.beans.property.*;
import javafx.stage.Stage;

public class MainController implements Serializable {

    @FXML
    public AnchorPane deletepane;
    @FXML
    public TextField addTaskTF;
    @FXML
    public AnchorPane landingPage;
    @FXML
    public TextField newTaskTF;
    @FXML
    public TextField toBeUpdatedTaskTF;
    @FXML
    public TextField deleteTaskTF;
    @FXML
    private TableView tasktable;

    @FXML
    public TableColumn<Task, String>  taskColoumn;

    public boolean SignInSuccessful;

    public static ObservableList<Task> getAllTask() throws ClassNotFoundException , SQLException{

        try {
            Connection connection = MySQLConnection.getConnection();
            assert connection != null;
            PreparedStatement statement =  connection.prepareStatement("SELECT `id` FROM `users` WHERE `username` = ?");
            statement.setString(1,SigInController.uname);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("user name " + SigInController.uname);
            if(resultSet.next()){
                System.out.println(resultSet.getString("id"));
            }

            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM `tbltask` WHERE `userID` = ?");
            statement1.setInt(1,SigInController.uid);
            ResultSet resultSet1 = statement1.executeQuery();

            return getTaskObjects(resultSet1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static ObservableList<Task> getTaskObjects(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        try{
            ObservableList<Task> taskList = FXCollections.observableArrayList();
            while (resultSet.next()){
                Task task = new Task();
                task.setTask(resultSet.getString("task"));
                taskList.add(task);
            }
            return  taskList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void initialize() throws Exception{

        if(SigInController.SignInSuccessful) {
            System.out.println("signin status " + SignInSuccessful);
            taskColoumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().task.get()));
            ObservableList<Task> tasksList = MainController.getAllTask();
            populateTable(tasksList);
        }
        System.out.println("Status "+SigInController.SignInSuccessful);
        System.out.println("name " + SigInController.uname);
    }

    private void populateTable(ObservableList<Task> tasksList) {
        tasktable.setItems(tasksList);
    }

    @FXML
    private void addTask() {
        try (Connection connection = MySQLConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO tbltask (userID,task) VALUES (?,?)")) {
                preparedStatement.setInt(1,SigInController.uid);
                preparedStatement.setString(2, addTaskTF.getText());
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully!");
                }else{
                    System.out.println("Data inserted Failed!");
                }
                Stage stage = (Stage) landingPage.getScene().getWindow();
                stage.close();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
                    Parent root = loader.load();
                    stage= new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void deleteTask() {
        try (Connection connection = MySQLConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM `tbltask` WHERE `task` = ?")) {

                preparedStatement.setString(1, deleteTaskTF.getText());
                int deletedRow = preparedStatement.executeUpdate();

                if (deletedRow > 0) {
                    System.out.println("Data deleted successfully!");
                }else{
                    System.out.println("Data deleted Failed!");
                }
                Stage stage = (Stage) landingPage.getScene().getWindow();
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
                    Parent root = loader.load();
                    stage= new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void updateTask() {
        try (Connection connection = MySQLConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `tbltask` SET`task`= ? WHERE `userID` = ? AND `task` = ?")) {

                preparedStatement.setString(1, newTaskTF.getText());
                preparedStatement.setInt(2,SigInController.uid);
                preparedStatement.setString(3, toBeUpdatedTaskTF.getText());
                int updatedRow = preparedStatement.executeUpdate();

                if (updatedRow > 0) {
                    System.out.println("Data updated successfully!");
                }else{
                    System.out.println("Data updated Failed!");
                }
                Stage stage = (Stage) landingPage.getScene().getWindow();
                stage.close();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
                    Parent root = loader.load();
                    stage= new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void exitApp(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) landingPage.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}