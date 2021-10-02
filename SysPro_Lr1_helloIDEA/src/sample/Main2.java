/*
package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

class A {
    A() {
    }
}

public class Main2<T> extends Application {
    private Map<Integer, Object> map = new HashMap<>();

    Main2() {
        map.put(1, new A());
        map.put(2, new String("vsd"));
        map.put(3, new Integer(3));
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        System.out.println(main2.getItem(2).getClass());
        System.out.println("vasdf");


        System.out.println("fdsf");
//        Application.launch(args);
    }

    public <E> E getItem(int i) {
        return (E) map.get(i);
    }

    @Override
    public String toString() {
        return "Main2{" +
                '}';
    }

    @Override
    public void start(Stage stage) throws Exception {
        int i = 5;
        Shape shape = null;
        if (i == 2) {
            shape = new Ellipse(10, 10, 100, 100);
        } else if (i == 3) {
            shape = new Line(90, 90, 200, 200);
        } else if (i == 4) {
            shape = new Rectangle(39, 39, 200, 200);
        } else if (i == 5) {
            shape = new Text(90, 90, "text");
        }

        shape.setStroke(Color.GRAY);

        BorderPane border = new BorderPane();
        border.setCenter(new TextField());
        border.setRight(new Button("Click me"));
        border.setMinWidth(300);
        border.setMinHeight(300);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(new Button("one"), new Button("two"));
        hbox.setMinHeight(300);
        hbox.setMinWidth(300);
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(5));

        Group group = new Group();
        group.getChildren().add(shape);
        group.getChildren().add(border);
        group.getChildren().add(hbox);
        Scene scene = new Scene(group, 300, 300);

        stage.setTitle("Hello world!");
        stage.setScene(scene);
        stage.show();
    }
}
*/


/*        Pane root = new Pane();
        primaryStage.setTitle("JavaFX Chart (Series)");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x,y);
        numberLineChart.setTitle("Series");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("cos(x)");
        series1.setName("sin(x)");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        for(int i=0; i<20; i++){
            datas.add(new XYChart.Data(i,Math.sin(i)));
            datas2.add(new XYChart.Data(i,Math.cos(i)));
        }

        series1.setData(datas);
        series2.setData(datas2);

        Scene scene = new Scene(numberLineChart, 600,600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        primaryStage.setScene(scene);

        primaryStage.show();*/