package ru.vivt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    public TextField numerator1, numerator2, denominator1, denominator2, result;

    @FXML
    public Button buttoncalc;

    @FXML
    public Button clear;

    @FXML
    public Label error;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttoncalc.setOnAction(e -> {
            try {
                double p = Integer.parseInt(this.numerator1.getText());
                double r = Integer.parseInt(this.numerator2.getText());
                double q = Integer.parseInt(this.denominator1.getText());
                double s = Integer.parseInt(this.denominator2.getText());

                if (q == 0.0d || s == 0.0d) {
                    throw new RuntimeException("Zero in denominator");
                }

                double result = ((p * s) + (r * q)) / (q * s);
                System.out.println(" ((p * s) + (r * q) ) / (q * s) = " + result);
                System.out.println(" ((p * s) + (r * q) ) = " + ((p * s) + (r * q)));
                System.out.println("(q * s) = " + (q * s));
                this.result.setText(String.valueOf(result));

                error.setText("");
            } catch (NumberFormatException exp) {
                exp.printStackTrace();
                error.setText("Не верный формат числа, исключение: " + exp.getMessage());
            } catch (RuntimeException exp2) {
                exp2.printStackTrace();
                error.setText("В этой программе нельзя делить на ноль");
            }
        });

        clear.setOnAction(e -> {
            numerator1.setText("");
            numerator2.setText("");
            denominator1.setText("");
            denominator2.setText("");
            result.setText("");
        });
    }

}
