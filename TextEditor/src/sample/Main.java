package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.scene.text.Font;

import java.util.stream.Collectors;

class CreateLabeled {
    static Button createButton(int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        Button obj = new Button();
        obj.setText(text);
        obj.setTranslateX(posX);
        obj.setTranslateY(posY);
        obj.setPrefSize(prefSizeX, prefSizeY);
        return obj;
    }

    static Label createLabel(int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        Label obj = new Label();
        obj.setText(text);
        obj.setTranslateX(posX);
        obj.setTranslateY(posY);
        obj.setPrefSize(prefSizeX, prefSizeY);
        return obj;
    }

    static RadioButton createRadioButton (int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        RadioButton obj = new RadioButton();
        obj.setText(text);
        obj.setTranslateX(posX);
        obj.setTranslateY(posY);
        obj.setPrefSize(prefSizeX, prefSizeY);
        return obj;
    }

    static CheckBox createCheckBox (int posX, int posY, int prefSizeX, int prefSizeY, String text) {
        CheckBox obj = new CheckBox();
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

        Button button = new Button();
        button.setText("Load");
        button.setTranslateX(20);
        button.setTranslateY(300);
        button.setPrefSize(60, 60);

        Label label = new Label("Text");
        label.setTranslateX(20);
        label.setTranslateY(20);

        TextField textField = new TextField();
        textField.setTranslateX(20);
        textField.setTranslateY(50);


        ComboBox<String> comboBoxFonts = new ComboBox<>();
        comboBoxFonts.setTranslateX(20);
        comboBoxFonts.setTranslateY(130);
        comboBoxFonts.getItems().addAll(Font.getFontNames().stream().limit(10).collect(Collectors.toList()));

        comboBoxFonts.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue()));
        });

        ComboBox<Integer> comboBoxFontSize = new ComboBox<>();
        comboBoxFontSize.setTranslateX(20);
        comboBoxFontSize.setTranslateY(170);
        for (int i = 8; i < 30; i++) {
            comboBoxFontSize.getItems().add(i);
        }
        comboBoxFontSize.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue(), comboBoxFontSize.getValue()));
        });

        ComboBox<Color> comboBoxColors = new ComboBox<>();
        comboBoxColors.setTranslateX(20);
        comboBoxColors.setTranslateY(200);
        comboBoxColors.getItems().addAll(Color.RED, Color.BLACK, Color.BLUE, Color.GRAY);
        comboBoxColors.setOnAction(actionEvent -> {
            textField.setFont(Font.font(comboBoxFonts.getValue(), comboBoxFontSize.getValue()));
            textField.setStyle("-fx-text-inner-color: red");
        });



//        group.getChildren().addAll(button1, button2);

        Scene scene = new Scene(root);
        root.getChildren().addAll(button);
        root.getChildren().addAll(label);
        root.getChildren().add(textField);
        root.getChildren().add(comboBoxFonts);
        root.getChildren().add(comboBoxFontSize);
        root.getChildren().add(comboBoxColors);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
