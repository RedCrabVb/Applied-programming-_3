package ru.vivt;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javax.sound.midi.Receiver;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    public LineChart<Number, Number> chart;

    @FXML
    public TextField textV;

    @FXML
    public TextField textLpha;

    @FXML
    public TextField textDt;

    @FXML
    public Circle circleModel;

    @FXML
    public Polygon polygonModel;

    @FXML
    public Label maximumRange; //tab 1

    @FXML
    public Label maxRange; //tab 3

    @FXML
    public Label maxHeight; //tab 3

    @FXML
    public Label maxTime; //tab 3

    @FXML
    public Button enterData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Double g = 9.8;
        Double pi = 3.145;

//        animationModel();

        enterData.setOnAction(e -> {
            Double v = Double.parseDouble(textV.getText());
            Double dt = Double.parseDouble(textDt.getText());
            Double alpha = Double.parseDouble(textLpha.getText());


            double t = 0, x = 0, y = 0;
            XYChart.Series series = new XYChart.Series();
            List<Double> listX = new LinkedList<>();
            List<Double> listY = new LinkedList<>();
            while (y >= 0) {
                x = v * Math.cos(alpha * pi / 180) * t;
                y = v * Math.sin(alpha * pi / 180) * t - (g * Math.pow(t, 2)) / 2;
                t += dt;

                listX.add(x);
                listY.add(y);

                series.getData().add(new XYChart.Data(String.valueOf(x), (y)));

                System.out.format("t = %s, x = %.2f, y = %.2f\n", t, x, y);
            }

            List<Double> points = new LinkedList<>();
            for (int i = 0; i < listX.size(); i++) {
                points.add(listX.get(i));
                points.add(-listY.get(i));
            }

            animationModel(points);

            chart.getData().clear();
            chart.getData().add(series);

            System.out.println((v * Math.sin(Math.toRadians(alpha))) / g);

            maxRange.setText(String.format("%.2f", Collections.max(listX)));
            maximumRange.setText(maxRange.getText());
            maxHeight.setText(String.format("%.2f", Collections.max(listY)));
            maxTime.setText(String.valueOf(t));

        });
    }

    private void animationModel(List<Double> points) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(points);

        polygonModel.getPoints().clear();
        polygonModel.getPoints().addAll(points);

        PathTransition transition = new PathTransition();
        transition.setNode(circleModel);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(polygon);
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(0);
        transition.play();
    }
}
