package test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable  {
    private ObservableList<HBoxCell> myObservableList;

    @FXML
    private ListView<HBoxCell> testViewList;

    @FXML
    public void addCaseTest(ActionEvent event) {
        System.out.println("click");
        myObservableList.add(new HBoxCell("Введите текст", "Баллы "));
        testViewList.setItems(myObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<HBoxCell> list = new ArrayList<>();
        myObservableList = FXCollections.observableList(list);
        addCaseTest(new ActionEvent());
    }


    static class HBoxCell extends HBox {
        Label labelText = new Label();
        Label labelBall = new Label();
        TextField textTest = new TextField();
        TextField textBall = new TextField();

        HBoxCell(String labelText, String labelBall) {
            super();

            this.labelText.setText(labelText);
            this.labelText.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(this.labelBall, Priority.ALWAYS);

            textBall.setMaxWidth(30);
            textTest.setMaxWidth(170);

            this.labelBall.setText(labelBall);

            this.getChildren().addAll(this.labelText, textTest, this.labelBall, textBall);
        }
    }

}


