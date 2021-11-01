package ru.vivt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    public TextField outputStr2, outputStr1, inputStr;

    @FXML
    public Button runTransform;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            runTransform.setOnAction(e -> {
                String input = inputStr.getText();

                StringBuilder out1 = new StringBuilder();
                StringBuilder out2 = new StringBuilder();

                for (int i = 0; i < input.length(); i++) {
                    if (i % 2 == 0) {
                        out2.append(input.charAt(i));
                    } else {
                        out1.append(input.charAt(i));
                    }
                }

                outputStr1.setText(out1.toString());
                outputStr2.setText(out2.toString());
            });
    }


}
