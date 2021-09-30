package test;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerGrable implements Serialized, Initializable {

    @FXML
    public TextField minBal;

    @FXML
    public TextField maxBal;

    @FXML
    public TextArea description;

    @FXML
    public TextField header;


    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("minBal", maxBal.getText());
        json.addProperty("maxBal", maxBal.getText());
        json.addProperty("description", description.getText());
        json.addProperty("header", header.getText());
        return json;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        minBal.setTextFormatter(TextFormantNumber.getTextFormatNumber());
        maxBal.setTextFormatter(TextFormantNumber.getTextFormatNumber());
    }
}
