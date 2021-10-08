package ru.vivt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import ru.vivt.repository.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerMain implements Initializable {
    @FXML
    public PieChart pieChartShopping;

    @FXML
    public RadioButton dayButton, weekButton, monthButton, yearButton;

    @FXML
    public ListView shoppingList;

    private ObservableList<AnchorPane> listOfShopping;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //листы
        Repository repository = new Repository("repository.json");
        repository.addPurchase("test1", "02_05_2021");
        repository.addPurchase("vvv", "04_06_2000");
        repository.addPurchase("aaa", "01_01_2020");

        listOfShopping = FXCollections.observableList(new ArrayList<>());
        shoppingList.setItems(listOfShopping);

        repository.getAll().forEach(p -> {
            addItemsInShoppingList(listOfShopping, p.getHeader(), p.getDate(), p.isCompleted());
        });

        //график
        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone"  , 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet" , 36);

        pieChartShopping.getData().add(slice1);
        pieChartShopping.getData().add(slice2);
        pieChartShopping.getData().add(slice3);

        //сортировка radioButton
        ToggleGroup toggleGroup = new ToggleGroup();
        dayButton.setToggleGroup(toggleGroup);
        weekButton.setToggleGroup(toggleGroup);
        monthButton.setToggleGroup(toggleGroup);
        yearButton.setToggleGroup(toggleGroup);

    }

    public void addItemsInShoppingList(ObservableList<AnchorPane> list, String textMain, String date, boolean completed) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("purchase.fxml"));
        loader.setControllerFactory(cls -> {
            if (cls == ControllerPurchase.class) {
                return new ControllerPurchase(textMain, date, completed);
            } else
                try {
                    return cls.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
        });

        try {
            AnchorPane anchorPaneTestNews = loader.load();
            list.add(anchorPaneTestNews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
