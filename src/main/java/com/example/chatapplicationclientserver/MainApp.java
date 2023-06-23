package com.example.chatapplicationclientserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {

        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Manager's App");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}