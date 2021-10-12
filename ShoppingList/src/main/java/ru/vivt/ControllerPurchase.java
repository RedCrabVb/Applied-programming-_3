package ru.vivt;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ru.vivt.repository.Purchase;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Supplier;

public class ControllerPurchase implements Initializable {
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    private Purchase purchase;
    private EventHandler<ActionEvent> var1;
    private Runnable clickDelete;

    public ControllerPurchase(Purchase purchase, EventHandler<ActionEvent> var1, Runnable clickDelete) {
        this.purchase = purchase;
        this.var1 = var1;
        this.clickDelete = clickDelete;
    }

    @FXML
    public Label textMain, textDate;

    @FXML
    public CheckBox completedCheck;

    @FXML
    public AnchorPane anchorPanePurchase;

    @FXML
    public Button delete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textMain.setText(purchase.getHeader());
        completedCheck.setSelected(purchase.isCompleted());

        if (purchase.getDate() != null) {
            textDate.setText(purchase.getDate().format(formatters));
        }

        anchorPanePurchase.setOnMouseClicked(mouseEvent -> System.out.println("click + " + purchase.getHeader()));

        completedCheck.setOnAction(e -> {
            purchase.setCompleted(completedCheck.isSelected());
            clickDelete.run();
        });

        delete.setOnAction(var1);
    }
}
