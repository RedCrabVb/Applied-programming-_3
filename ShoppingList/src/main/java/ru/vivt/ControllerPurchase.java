package ru.vivt;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ru.vivt.repository.Purchase;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerPurchase implements Initializable {
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    private Purchase purchase;

    public ControllerPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @FXML
    public Label textMain, textDate;

    @FXML
    public CheckBox completedCheck;

    @FXML
    public AnchorPane anchorPanePurchase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textMain.setText(purchase.getHeader());
        completedCheck.setSelected(purchase.isCompleted());

        if (purchase.getDate() != null) {
            textDate.setText(purchase.getDate().format(formatters));
        }

        anchorPanePurchase.setOnMouseClicked(mouseEvent -> System.out.println("click + " + purchase.getHeader()));

        completedCheck.setOnAction(e -> {
            System.out.println(purchase.getHeader() + " completed = " + completedCheck.isSelected());
            purchase.setCompleted(completedCheck.isSelected());
        });
    }
}
