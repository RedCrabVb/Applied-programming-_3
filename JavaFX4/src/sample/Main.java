package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tabulating function");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        String str = "98+4";
        List<String> listNumber = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
           boolean number = false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
