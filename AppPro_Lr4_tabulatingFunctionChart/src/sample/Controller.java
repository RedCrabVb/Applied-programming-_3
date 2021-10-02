package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    private RadioButton max;

    @FXML
    private RadioButton min;

    @FXML
    private RadioButton avg;

    @FXML
    private TextField xStart;

    @FXML
    private TextField xEnd;

    @FXML
    private TextField hx;

    @FXML
    private Label resultText;

    @FXML
    public void calculate(ActionEvent event) {
        try {
            double d_xStart = Double.parseDouble(xStart.getText());
            double d_xEnd = Double.parseDouble(xEnd.getText());
            double d_dx = Double.parseDouble(hx.getText());

            LinkedList<Double> listValues = new LinkedList<>();
            XYChart.Series series = new XYChart.Series();
            for (double i = d_xStart; i < d_xEnd; i += d_dx) {
                listValues.add(func(i));

                series.getData().add(new XYChart.Data(String.valueOf(i), (listValues.getLast())));
            }

            //debug
            listValues.forEach(System.out::println);

            String result = "Результат: ";
            if (max.isSelected()) {
                result += String.format("max y(x) = %.2f", Collections.max(listValues));
            }
            if (min.isSelected()) {
                result += String.format("min y(x) = %.2f", Collections.min(listValues));
            }
            if (avg.isSelected()) {
                result += String.format("avg = %.2f", listValues.stream().mapToDouble(a -> a).average().getAsDouble());
            }
            resultText.setText(result);

            chart.getData().clear();
            chart.getData().add(series);
        } catch (NumberFormatException e) {
            resultText.setText(e.getMessage());
        }
    }

    private static double func(double x) {
        //y=(x2+1)∙sin(3x)
        return (Math.pow(x, 2) + 1) * Math.sin(3 * x);
    }
}
