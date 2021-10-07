package ru.vivt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add
                (getClass().getClassLoader().getResource("main.css").toExternalForm());
        primaryStage.setTitle("Graphics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
