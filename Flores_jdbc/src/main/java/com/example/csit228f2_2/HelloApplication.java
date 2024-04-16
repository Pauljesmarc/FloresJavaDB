package com.example.csit228f2_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane pnMain = new AnchorPane();
        GridPane grid = new GridPane();
        pnMain.getChildren().add(grid);

        grid.setAlignment(Pos.CENTER);
        Text sceneTitle = new Text("Welcome to CSIT228");

        sceneTitle.setStrokeType(StrokeType.CENTERED);
        sceneTitle.setStrokeWidth(100);
        sceneTitle.setFill(Paint.valueOf("#325622"));
        sceneTitle.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 69));

        grid.add(sceneTitle, 0, 0, 2, 1);


        Button btncreate = new Button("Create/Inserting");
        btncreate.setFont(Font.font(45));
        HBox hbSignIn = new HBox();
        hbSignIn.getChildren().add(btncreate);
        hbSignIn.setAlignment(Pos.CENTER);
        grid.add(hbSignIn, 0, 3, 2, 1);

        Button btnread = new Button("Read/Retrieving");
        btnread.setFont(Font.font(45));
        HBox hbbtnSignIn = new HBox();
        hbbtnSignIn.getChildren().add(btnread);
        hbbtnSignIn.setAlignment(Pos.CENTER);
        grid.add(hbbtnSignIn, 0, 4, 2, 1);

        Button btnupdate = new Button("Update/Updating");
        btnupdate.setFont(Font.font(45));
        HBox hbbtnupdate = new HBox();
        hbbtnupdate.getChildren().add(btnupdate);
        hbbtnupdate.setAlignment(Pos.CENTER);
        grid.add(hbbtnupdate, 0, 6, 2, 1);

        Button btndelete = new Button("Delete/Deleting");
        btndelete.setFont(Font.font(45));
        HBox hbbtndelete = new HBox();
        hbbtndelete.getChildren().add(btndelete);
        hbbtndelete.setAlignment(Pos.CENTER);
        grid.add(hbbtndelete, 0, 5, 2, 1);


        final Text actionTarget = new Text(" ");
        actionTarget.setFont(Font.font(30));
        grid.add(actionTarget, 1, 6);

        btncreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnread.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-in.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnupdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btndelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        Scene scene = new Scene(pnMain, 700, 560);
        stage.setScene(scene);
        stage.show();
    }

}