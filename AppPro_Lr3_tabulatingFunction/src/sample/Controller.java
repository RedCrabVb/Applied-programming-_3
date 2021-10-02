package com.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
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

            List<Double> listValues = new ArrayList<>();
            for (double i = d_xStart; i < d_xEnd; i += d_dx) {
                listValues.add(func(i));
            }

            //debug
            listValues.forEach(System.out::println);

            String result = "Результат: ";
            if (max.isSelected()) {
                result += String.format("%.2f", Collections.max(listValues));
            }
            if (min.isSelected()) {
                result += String.format("%.2f", Collections.min(listValues));
            }
            if (avg.isSelected()) {
                result += String.format("%.2f", listValues.stream().mapToDouble(a -> a).average().getAsDouble());
            }
            resultText.setText(result);
        } catch (NumberFormatException e) {
            resultText.setText(e.getMessage());
            return;
        }
    }

    private static double func(double x) {
        //y=(x2+1)∙sin(3x)
        return (Math.pow(x, 2) + 1) * Math.sin(3 * x);
    }
}
