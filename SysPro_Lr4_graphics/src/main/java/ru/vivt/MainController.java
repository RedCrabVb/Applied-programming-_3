package ru.vivt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        circle.setToggleGroup(group);
        square.setToggleGroup(group);
        triangle.setToggleGroup(group);

        //"Белый", "Черный", "Красный", "Желтый", "Оранжевый", "Зеленый", "Синий", "Фиолетовый", "Розовый", "Коричневый", "Серый"
        Map<String, Color> mapsColors = new HashMap<>();
        mapsColors.put("Белый", Color.WHITE);
        mapsColors.put("Черный", Color.BLACK);

        ObservableList<String> colorsList = FXCollections.observableArrayList(mapsColors.keySet());
        lineColor.setItems(colorsList);
        fillColor.setItems(colorsList);

        drawShapes.setOnAction(e -> {
            System.out.println(mapsColors.get(lineColor.getValue()));

            planeShapes.getChildren().clear();

            int thickness = 1;
            try {
                thickness = Integer.parseInt(lineThickness.getText());
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            if (circle.isSelected()) {
                Circle shapes = new Circle();
                shapes.setRadius(planeShapes.getWidth() / 3);
                shapes.setCenterX(planeShapes.getWidth() / 2);
                shapes.setCenterY(planeShapes.getHeight() / 2);
                shapes.setFill(mapsColors.get(fillColor.getValue()));
                shapes.setStroke(mapsColors.get(lineColor.getValue()));
                shapes.setStrokeWidth(thickness);
                planeShapes.getChildren().add(shapes);
            } else if (square.isSelected()) {
                Rectangle shapes = new Rectangle();
                shapes.setHeight(planeShapes.getHeight() / 2);
                shapes.setWidth(shapes.getHeight());
                shapes.setFill(mapsColors.get(fillColor.getValue()));
                shapes.setStroke(mapsColors.get(lineColor.getValue()));
                shapes.setStrokeWidth(thickness);
                planeShapes.getChildren().add(shapes);
            } else if(triangle.isSelected()) {
                Polygon shapes = new Polygon();
                shapes.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        planeShapes.getHeight() - 40, planeShapes.getWidth() - 40,
                        planeShapes.getWidth()  - 50, planeShapes.getHeight() - 70 });
                shapes.setFill(mapsColors.get(fillColor.getValue()));
                shapes.setStroke(mapsColors.get(lineColor.getValue()));
                shapes.setStrokeWidth(thickness);
                planeShapes.getChildren().add(shapes);
            }
        });
    }
}
