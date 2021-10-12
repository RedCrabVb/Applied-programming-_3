package ru.vivt;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.vivt.repository.Purchase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Supplier;

public class ControllerPurchase implements Initializable {
    @FXML
    public Label textMain, textDate;
    @FXML
    public CheckBox completedCheck;
    @FXML
    public AnchorPane anchorPanePurchase;
    @FXML
    public Button delete;
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    private Purchase purchase;
    private EventHandler<ActionEvent> var1;
    private Runnable clickDelete;

    public ControllerPurchase(Purchase purchase, EventHandler<ActionEvent> var1, Runnable clickDelete) {
        this.purchase = purchase;
        this.var1 = var1;
        this.clickDelete = clickDelete;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textMain.setText(purchase.getHeader());
        completedCheck.setSelected(purchase.isCompleted());

        if (purchase.getDate() != null) {
            textDate.setText(purchase.getDate().format(formatters));
        }

        anchorPanePurchase.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("record_view_window.fxml"));
                loader.setControllerFactory(cls -> {
                    if (cls == RecordViewWindow.class) {
                        return new RecordViewWindow(purchase.getHeader(), purchase.getPrice(), purchase.getDate(), purchase.getNote(), purchase.getCategory());
                    } else
                        try {
                            return cls.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                });

                Stage stage = new Stage();
                stage.setTitle("Покупка");
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        completedCheck.setOnAction(e -> {
            purchase.setCompleted(completedCheck.isSelected());
            clickDelete.run();
        });

        delete.setOnAction(var1);
    }
}
