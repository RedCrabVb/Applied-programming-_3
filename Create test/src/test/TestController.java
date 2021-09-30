package test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable, Serialized {
    private ObservableList<HBoxCell> answerOptions;

    @FXML
    private ListView<HBoxCell> testViewList;

    @FXML
    private TextArea questionText;

    @FXML
    public void addCaseTest(ActionEvent event) {
        answerOptions.add(new HBoxCell("Введите текст", "Баллы "));
        testViewList.setItems(answerOptions);

//        myObservableList.forEach(x -> System.out.println(x.toJson().toString()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<HBoxCell> list = new ArrayList<>();
        answerOptions = FXCollections.observableList(list);
        addCaseTest(new ActionEvent());
    }


    static class HBoxCell extends HBox implements Serialized {
        Label labelText = new Label();
        Label labelBall = new Label();
        TextField textTest = new TextField();
        TextField textBall = new TextField();

        HBoxCell(String labelText, String labelBall) {
            super();

            this.labelText.setText(labelText);
            this.labelText.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(this.labelBall, Priority.ALWAYS);

            textBall.setMaxWidth(50);
            textTest.setMaxWidth(180);

            this.labelBall.setText(labelBall);

            textBall.setTextFormatter(TextFormantNumber.getTextFormatNumber());
            this.getChildren().addAll(this.labelText, textTest, this.labelBall, textBall);
        }

        @Override
        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("labelText", labelText.getText().toString());
//            jsonObject.addProperty("labelBall", labelBall.getText().toString());
            jsonObject.addProperty("textTest", textTest.getText().toString());
            jsonObject.addProperty("textBall", textBall.getText().toString());

            return jsonObject;
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonAnswerOptions = new JsonArray();
        answerOptions.forEach(x -> jsonAnswerOptions.add(x.toJson()));
        jsonObject.add("answerOptions", jsonAnswerOptions);
        jsonObject.addProperty("questionText", questionText.getText().toString());
        return jsonObject;
    }
}


