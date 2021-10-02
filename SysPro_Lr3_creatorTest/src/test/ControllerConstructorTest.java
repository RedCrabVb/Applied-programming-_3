package test;

import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerConstructorTest implements Initializable {
    private ObservableList<AnchorPane> listOfQuestions;
    private List<FXMLLoader> fxmlLoaders = new ArrayList<>();

    @FXML
    private ListView listView;

    private Scene sceneNext;
    private Scene scenePrevision;

    public void setScene(Scene scene) {
        this.sceneNext = scene;
    }

    public void setPrevision(Scene scene) {
        this.scenePrevision = scene;
    }

    @FXML
    public void onNextScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneNext);

        JsonArray jsonArray = new JsonArray();
        fxmlLoaders.forEach(x -> {
            Serialized testController = x.getController();
            jsonArray.add(testController.toJson());
        });
        try {
            FileWriter fileWriter = new FileWriter(new File("test.json"));
            fileWriter.write(jsonArray.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onPrevisionScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scenePrevision);
    }

    @FXML
    public void addTest(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/test/fxml/test.fxml"));
            AnchorPane anchorPaneTestNews = fxmlLoader.load();
            listOfQuestions.add(anchorPaneTestNews);

            fxmlLoaders.add(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<AnchorPane> list = new ArrayList<>();
        listOfQuestions = FXCollections.observableList(list);
        listView.setItems(listOfQuestions);
        addTest(new ActionEvent());
    }
}