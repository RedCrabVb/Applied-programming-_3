package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoaderSampleMain = new FXMLLoader(getClass().getResource("../fxml/sampleMain.fxml"));
        Parent rootStart = fxmlLoaderSampleMain.load();
        Controller controllerSampleMain = (Controller) fxmlLoaderSampleMain.getController();
        primaryStage.setTitle("constructor test");
        primaryStage.setScene(new Scene(rootStart, 480, 340));
        primaryStage.show();

        FXMLLoader fxmlLoaderCreateTest =  new FXMLLoader(getClass().getResource("../fxml/createrTest.fxml"));
        Parent rootCreateTest = fxmlLoaderCreateTest.load();
        ControllerCreaterTest controllerCreaterTest = (ControllerCreaterTest) fxmlLoaderCreateTest.getController();
        Scene sceneCreateTest = new Scene(rootCreateTest, 560, 410); //name 'constructor test' for variable better

        FXMLLoader fxmlLoaderResultEdit = new FXMLLoader(getClass().getResource("../fxml/resultEdit.fxml"));
        Parent rootResultEdit = fxmlLoaderResultEdit.load();
        ResultEdit controllerResultEdit = (ResultEdit) fxmlLoaderResultEdit.getController();
        Scene sceneResultEdit = new Scene(rootResultEdit, 500, 410);
        controllerSampleMain.setScene(sceneCreateTest);
        controllerCreaterTest.setScene(sceneResultEdit);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
