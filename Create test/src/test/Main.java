package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sampleMain.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = (Controller) fxmlLoader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 550, 450));
        primaryStage.show();

        FXMLLoader fxmlLoader2 =  new FXMLLoader(getClass().getResource("createrTest.fxml"));
        Parent root2 = fxmlLoader2.load();
        Scene secondScene = new Scene(root2, 550, 450);
        controller.setScene(secondScene);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
