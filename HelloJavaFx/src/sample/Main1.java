package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
6. Разместите форме ряд кнопок (вutton) и ряд меток (Label).
Создайте обработчик события создания формы (Load), который будет делать
 все метки невидимыми. Создайте обработчики события нажатия на кнопки,
  которые будут менять свойство метки Visible, тем самым делать их видимыми.
 */
class CreateLabeled {
/*    static Button createButton(int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        Button obj = new Button();
        obj.setText(text);
        obj.setTranslateX(posX);
        obj.setTranslateY(posY);
        obj.setPrefSize(prefSizeX, prefSizeY);
        return obj;
    }*/

    static Label createLabel(int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        Label obj = new Label();
        obj.setText(text);
        obj.setTranslateX(posX);
        obj.setTranslateY(posY);
        obj.setPrefSize(prefSizeX, prefSizeY);
        return obj;
    }
}

public class Main1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MyFirstScene");
        primaryStage.setWidth(900);
        primaryStage.setHeight(900);

        Pane root = new Pane();

        Button button = new Button();
        button.setText("Load");
        button.setTranslateX(200);
        button.setTranslateY(200);
        button.setPrefSize(60, 60);


        List<Label> labels = new ArrayList<>();
        labels.add(CreateLabeled.createLabel(10, 10, 50, 50, "label1"));
        labels.add(CreateLabeled.createLabel(10, 60, 50, 50, "label2"));
        labels.add(CreateLabeled.createLabel(10, 120, 50, 50, "label3"));

        button.setOnAction(actionEvent -> {
            labels.forEach(d -> d.setVisible(!d.isVisible()));
        });

        Scene scene = new Scene(root);
        root.getChildren().addAll(button);
        root.getChildren().addAll(labels);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
