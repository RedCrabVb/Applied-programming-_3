package ru.vivt;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    public RadioButton circle, square, triangle;

    @FXML
    public Button drawShapes;

    @FXML
    public Pane planeShapes;

    private Shape shape;

    private Text textError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        circle.setToggleGroup(group);
        square.setToggleGroup(group);
        triangle.setToggleGroup(group);

        textError = new Text("Error input data");
        textError.setX(20);
        textError.setFont(Font.font("System", 25));

        Map<String, Color> mapsColors = new HashMap<>();
        mapsColors.put("Белый", Color.WHITE);
        mapsColors.put("Черный", Color.BLACK);
        mapsColors.put("Красный", Color.RED);
        mapsColors.put("Желтый", Color.YELLOW);
        mapsColors.put("Оранжевый", Color.ORANGE);
        mapsColors.put("Зеленый", Color.GREEN);
        mapsColors.put("Синий", Color.BLUE);
        mapsColors.put("Фиолетовый", Color.PURPLE);
        mapsColors.put("Розовый", Color.PINK);
        mapsColors.put("Коричневый", Color.BROWN);
        mapsColors.put("Серый", Color.GRAY);

        circle.setOnAction(e -> {
            Circle shape = new Circle();

            shape.setRadius(planeShapes.getWidth() / 3);
            shape.setCenterX(planeShapes.getWidth() / 2);
            shape.setCenterY(planeShapes.getHeight() / 2);

            this.shape = shape;
        });
        square.setOnAction(e -> {
            Rectangle shape = new Rectangle();

            shape.setHeight(planeShapes.getHeight() - planeShapes.getHeight() / 2);
            shape.setWidth(shape.getHeight());
            shape.setX(75);
            shape.setY(75);

            this.shape = shape;
        });
        triangle.setOnAction(e -> {
            Line shapes = new Line();
            shapes.setStartX(190);
            shapes.setStartY(190);
            shapes.setEndX(20);
            shapes.setEndY(20);


            this.shape = shapes;
        });

        drawShapes.setOnAction(e -> {
            planeShapes.getChildren().clear();

            try {
                Optional.of(group.getSelectedToggle()).orElseThrow();

                this.shape.setFill(Color.RED);
                this.shape.setStroke(Color.BLUE);
                this.shape.setStrokeWidth(4);

                planeShapes.getChildren().add(this.shape);
                animation();
            } catch (Exception exception) {
                textError.setY(planeShapes.getWidth() / 2);
                this.shape = textError;
                Toggle toggle = group.getSelectedToggle();
                if (toggle != null) {
                    toggle.setSelected(false);
                }

                System.out.println(exception.getMessage());
                planeShapes.getChildren().add(this.shape);
            }

        });
    }

    private void animation() {
        //Duration = 2.5 seconds
        Duration duration = Duration.millis(2500);
        //Create new rotate transition
        RotateTransition rotateTransition = new RotateTransition(duration, shape);
        //Rotate by 200 degree
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }
}
