package sample;

import com.sun.javafx.geometry.BoundsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField textEditA;

    @FXML
    private TextField textEditB;

    @FXML
    private TextField textEditC;

    @FXML
    private TextArea resultText;

    @FXML
    public void decide(ActionEvent event) {
        try {
            double a = Double.parseDouble(textEditA.getText());
            double b = Double.parseDouble(textEditB.getText());
            double c = Double.parseDouble(textEditC.getText());

            resultText.setText(decideQuadraticEquation(a, b, c));
        } catch (NumberFormatException e) {
            resultText.setText(e.getMessage());
        }
    }

    @FXML
    public void clickHref() {
        try {
            Runtime.getRuntime().exec("google-chrome-unstable https://betacode.net/11533/open-a-new-window-in-javafx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decideQuadraticEquation(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        if (D > 0) {
            double x1, x2;
            x1 = Math.round((-b - Math.sqrt(D)) / (2 * a));
            x2 = Math.round((-b + Math.sqrt(D)) / (2 * a));
            return  "D = "  + Math.round(D) + "\n\n\nКорни уравнения: x1 = " + x1 + ", x2 = " + x2;
        }
        else if (D == 0) {
            double x;
            x = -b / (2 * a);
            return "D = "  + Math.round(D) + "\n\n\nУравнение имеет единственный корень: x = " + x;
        }
        else {
            return "D = "  + Math.round(D) + "\n\n\nУравнение не имеет действительных корней!";
        }
    }
}
