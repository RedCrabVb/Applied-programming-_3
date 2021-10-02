package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoaderSampleMain = new FXMLLoader(getClass().getResource("fxml/sampleMain.fxml"));
        FXMLLoader fxmlLoaderConstructorTest =  new FXMLLoader(getClass().getResource("fxml/constructorTest.fxml"));
        FXMLLoader fxmlLoaderResultEdit = new FXMLLoader(getClass().getResource("fxml/resultEdit.fxml"));
        FXMLLoader fxmlLoaderEnd = new FXMLLoader(getClass().getResource("fxml/sampleEnd.fxml"));

        Parent rootStart = fxmlLoaderSampleMain.load();
        Parent rootConstructorTest = fxmlLoaderConstructorTest.load();
        Parent rootResultEdit = fxmlLoaderResultEdit.load();
        Parent rootEnd = fxmlLoaderEnd.load();

        ControllerMain controllerSampleMain = fxmlLoaderSampleMain.getController();
        ControllerConstructorTest controllerConstructorTest = fxmlLoaderConstructorTest.getController();
        ControllerResultEdit controllerResultEdit = fxmlLoaderResultEdit.getController();

        Scene sceneMain = new Scene(rootStart);
        Scene sceneConstructorTest = new Scene(rootConstructorTest);
        Scene sceneConstructorGrade = new Scene(rootResultEdit);
        Scene sceneEnd = new Scene(rootEnd);

        controllerSampleMain.setScene(sceneConstructorTest);

        controllerConstructorTest.setScene(sceneConstructorGrade);
        controllerConstructorTest.setPrevision(sceneMain);

        controllerResultEdit.setScene(sceneEnd);
        controllerResultEdit.setPrevision(sceneConstructorTest);


        primaryStage.setTitle("constructor test");
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
