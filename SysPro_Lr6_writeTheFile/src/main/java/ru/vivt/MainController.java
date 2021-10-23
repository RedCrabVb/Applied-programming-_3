package ru.vivt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    public Button generate, load;

    @FXML
    public ComboBox select;

    @FXML
    public Label notFound;

    @FXML
    public ListView generatorList, loadList, filterList;

    private final static int MaxStudent = 30;
    private final String nameFile = "student.txt";

    private final String five = "Отличники", two = "Отчислен", other = "Успевающий";
    private List<String> listLoadStudent = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        select.getItems().add(five);
        select.getItems().add(two);
        select.getItems().add(other);

        generate.setOnAction(actionEvent -> {
            List<String> listStudent = new ArrayList<>();
            for (int i = 1; i < MaxStudent; i++) {
                var ball = new Random().ints(2, 6).limit(5).toArray();
                listStudent.add(String.format("Student %d|%s\n", i, ballToString(ball)));
            }
            generatorList.getItems().addAll(listStudent);

            try (FileWriter fileWriter = new FileWriter(nameFile)){
                for (var s : listStudent) {
                    fileWriter.write(s);
                }
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        load.setOnAction(e -> {
            listLoadStudent.clear();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile))) {
                String student;
                while ((student = bufferedReader.readLine()) != null) {
                    listLoadStudent.add(student);
                    System.out.println(student);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            filterList.getItems().clear();
            loadList.getItems().clear();
            loadList.getItems().addAll(listLoadStudent);
        });

        select.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            filterList.getItems().clear();
            for (var student : listLoadStudent) {
                String ballStr = student.split("(\\|)")[1];
                String[] listBallStr = ballStr.split(" ");

                List<Integer> ball = new LinkedList<>();

                for (var b : listBallStr) {
                    ball.add(Integer.parseInt(b));
                }

                String studentPrint = student.replace("Student", "Студент").replace("|", " групппы ПКС-019, успеваемость - ");
                if (newValue.equals(five)) {
                    if (ball.stream().filter(f -> f == 5).toArray().length >= 3) {
                        filterList.getItems().add(studentPrint);
                    }
                }
                if (newValue.equals(two)) {
                    if (ball.stream().filter(f -> f == 2).toArray().length >= 2) {
                        filterList.getItems().add(studentPrint);
                    }
                }
                if (newValue.equals(other)) {
                    if (ball.stream().filter(f -> f == 2).toArray().length < 2 && ball.stream().filter(f -> f == 5).toArray().length < 3) {
                        filterList.getItems().add(studentPrint);
                    }
                }
            }

            if (filterList.getItems().size() == 0) {
                notFound.setText("Not found data");
            } else {
                notFound.setText(" ");
            }
        });
    }

    private String ballToString(int[] ball) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var i : ball) {
            stringBuilder.append(String.format("%s ", i));
        }
        return stringBuilder.toString();
    }
}
