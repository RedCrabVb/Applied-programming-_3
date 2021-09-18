package test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerResultEdit implements Initializable {
    private ObservableList<AnchorPane> myObservableList;
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
    }

    @FXML
    public void onPrevisionScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scenePrevision);
    }

    public void addGrade(ActionEvent actionEvent) {
        try {
            AnchorPane anchorPaneTestNews = FXMLLoader.load(getClass().getResource("/fxml/grable.fxml"));
            myObservableList.add(anchorPaneTestNews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<AnchorPane> list = new ArrayList<>();
        myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        addGrade(new ActionEvent());
    }
}
