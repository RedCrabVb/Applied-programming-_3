package ru.vivt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RecordViewWindow implements Initializable {
    @FXML
    public TextField headerPurchaseFiled, priceFiled;

    @FXML
    public DatePicker dayNotificationsField;

    @FXML
    public TextArea noteFiled;

    @FXML
    public TextField category;

    private String headText;
    private Integer price;
    private LocalDate notification;
    private String note;
    private String categoryStr;
    public RecordViewWindow(String headText, Integer price, LocalDate notification, String note, String categoryStr) {

        this.headText = headText;
        this.price = price;
        this.notification = notification;
        this.note = note;
        this.categoryStr = categoryStr;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        headerPurchaseFiled.setText(headText);
        priceFiled.setText(String.valueOf(price));
        dayNotificationsField.setValue(notification);
        noteFiled.setText(note);
        this.category.setText(categoryStr);
    }
}
