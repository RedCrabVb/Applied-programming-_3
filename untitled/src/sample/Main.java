package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
/*        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
        primaryStage.setTitle("TextEditor");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        Pane root = new Pane();

        Label label = new Label("Text");
        label.setTranslateX(20);
        label.setTranslateY(20);

        TextField textField = new TextField();
        textField.setTranslateX(20);
        textField.setTranslateY(50);
        textField.setFont(Font.font(Font.getFontNames().stream().findFirst().get(), 14));


        int posCombatY = 120;
        ComboBox<String> comboBoxFonts = new ComboBox<>();
        comboBoxFonts.setTranslateX(20);
        comboBoxFonts.setTranslateY(posCombatY);
        comboBoxFonts.getItems().addAll(Font.getFontNames().stream().limit(30).collect(Collectors.toList()));
        comboBoxFonts.setValue(comboBoxFonts.getItems().get(0));

        ComboBox<Integer> comboBoxFontSize = new ComboBox<>();
        comboBoxFontSize.setTranslateX(20);
        comboBoxFontSize.setTranslateY((posCombatY = posCombatY + 40));
        for (int i = 8; i < 30; i++) {
            comboBoxFontSize.getItems().add(i);
        }
        comboBoxFontSize.setValue(comboBoxFontSize.getItems().get(6));

        ComboBox<String> comboBoxColors = new ComboBox<>();
        comboBoxColors.setTranslateX(20);
        comboBoxColors.setTranslateY((posCombatY = posCombatY + 40));
        comboBoxColors.getItems().addAll("black", "red", "blue", "gray");
        comboBoxColors.setValue(comboBoxColors.getItems().get(0));

        ComboBox<FontWeight> comboBoxWight = new ComboBox<>();
        comboBoxWight.setTranslateX(20);
        comboBoxWight.setTranslateY((posCombatY = posCombatY + 40));
        comboBoxWight.getItems().addAll(FontWeight.NORMAL, FontWeight.BLACK, FontWeight.LIGHT, FontWeight.SEMI_BOLD, FontWeight.EXTRA_BOLD);
        comboBoxWight.setValue(comboBoxWight.getItems().get(0));

        comboBoxColors.setOnAction(actionEvent -> {
            textField.setStyle(String.format("-fx-text-inner-color: %s", comboBoxColors.getValue()));
        });
        comboBoxFonts.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue(), comboBoxWight.getValue(), comboBoxFontSize.getValue()));
        });
        comboBoxFontSize.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue(), comboBoxWight.getValue(), comboBoxFontSize.getValue()));
        });
        comboBoxWight.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue(), comboBoxWight.getValue(), comboBoxFontSize.getValue()));
        });


        Scene scene = new Scene(root);
        root.getChildren().addAll(label, textField, comboBoxFonts, comboBoxFontSize, comboBoxWight, comboBoxColors);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
