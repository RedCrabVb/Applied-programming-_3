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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    public TextField lineThickness;

    @FXML
    public ComboBox lineColor, fillColor;

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

        ObservableList<String> colorsList = FXCollections.observableArrayList(mapsColors.keySet());
        lineColor.setItems(colorsList);
        fillColor.setItems(colorsList);

        circle.setOnAction(e -> {
            Circle shape = new Circle();

            shape.setRadius(planeShapes.getWidth() / 3);
            shape.setCenterX(planeShapes.getWidth() / 2);
            shape.setCenterY(planeShapes.getHeight() / 2);

            this.shape = shape;
        });
        square.setOnAction(e -> {
            Rectangle shape = new Rectangle();

            shape.setHeight(planeShapes.getHeight() - 50);
            shape.setWidth(shape.getHeight());
            shape.setX(30);
            shape.setY(30);

            this.shape = shape;
        });
        triangle.setOnAction(e -> {
            Polygon shapes = new Polygon();

            shapes.getPoints().addAll(new Double[]{
                    10.0, 10.0,
                    90.0, 190.0,
                    220.0, 90.0});

            this.shape = shapes;
        });

        drawShapes.setOnAction(e -> {
            planeShapes.getChildren().clear();

            try {
                Optional.of(group.getSelectedToggle()).orElseThrow();

                this.shape.setFill(Optional.of(mapsColors.get(fillColor.getValue())).orElseThrow());
                this.shape.setStroke(Optional.of(mapsColors.get(lineColor.getValue())).orElseThrow());
                this.shape.setStrokeWidth(Integer.parseInt(lineThickness.getText()));

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
