package ru.vivt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillRect(x, y, size, size);
            }
        });
    }

    public void onSave() {
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Chose as path out file");
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.dir"))
            );

            File selectFile = fileChooser.showSaveDialog(stage);

            WritableImage writableImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
            canvas.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", selectFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onLoad() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose as image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png", "*.jpeg"));

        File selectFile = fileChooser.showOpenDialog(stage);
        Image photo = new Image(selectFile.toURI().toString());
        canvas.getGraphicsContext2D().drawImage(photo, 0, 0);
    }
}

