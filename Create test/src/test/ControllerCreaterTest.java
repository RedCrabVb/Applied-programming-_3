package test;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class ControllerCreaterTest implements Initializable {
    private ObservableList<AnchorPane> myObservableList;
    private static FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private ListView listView;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void onSecondScene(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    public void addTest(ActionEvent event) {
        try {
            AnchorPane anchorPaneTestNews = fxmlLoader.load(getClass().getResource("/fxml/test.fxml"));
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
        addTest(new ActionEvent());
    }
}