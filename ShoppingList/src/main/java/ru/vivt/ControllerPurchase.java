package ru.vivt;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPurchase implements Initializable {
    private String text, data;
    private boolean completed;

    public ControllerPurchase(String text, String data, boolean completed) {
        this.text = text;
        this.data = data;
        this.completed = completed;
    }

    @FXML
    public Label textMain, textDate;

    @FXML
    public CheckBox completedCheck;

    @FXML
    public AnchorPane anchorPanePurchase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textMain.setText(text);
        textDate.setText(data);
        completedCheck.setSelected(completed);

        anchorPanePurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("click + " + text);
            }
        });

        completedCheck.setOnAction(e -> {
            System.out.println(text + " completed = " + completedCheck.isSelected());
        });
    }
}
