package test;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerMain {
    private Scene scene;

    @FXML
    public TextField mainTestText;

    @FXML
    public TextArea aboutTestText;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void onSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);

        try {
            JsonObject json = new JsonObject();
            json.addProperty("mainTestText", mainTestText.getText().toString());
            json.addProperty("aboutTestText", aboutTestText.getText().toString());

            FileWriter fileWriter = new FileWriter(new File("testAbout.json"));
            fileWriter.write(json.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
