package ru.vivt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.vivt.repository.Purchase;
import ru.vivt.repository.Repository;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ControllerMain implements Initializable {
    @FXML
    public PieChart pieChartShopping;

    @FXML
    public RadioButton dayButton, weekButton, monthButton, yearButton;

    @FXML
    public ListView<AnchorPane> shoppingList,
            shoppingListDate,
            shoppingListNotDate,
            shoppingListDateCompleted;

    @FXML
    public Button addPurchase;

    @FXML
    public TextField headerPurchaseFiled, priceFiled;

    @FXML
    public DatePicker dayNotificationsField;

    @FXML
    public TextArea noteFiled;


    private LocalDate localDateMinimum;

    private ObservableList<AnchorPane> listOfShoppingObservable,
            shoppingListDateObservable,
            shoppingListNotDateObservable,
            shoppingListDateCompletedObservable;


    private Repository repository;

    private void updateList(LocalDate localDateMinimum) {
        this.localDateMinimum = localDateMinimum;

        shoppingListDate.getItems().clear();
        shoppingListNotDate.getItems().clear();
        shoppingListDateCompleted.getItems().clear();
        repository.getAllPurchase().forEach(p -> {
            if (p.isCompleted()) {
                addItemsInShoppingList(shoppingListDateCompletedObservable, p);
            } else if (p.getDate() != null) {
                addItemsInShoppingList(shoppingListDateObservable, p);
            } else {
                addItemsInShoppingList(shoppingListNotDateObservable, p);
            }
        });

        shoppingList.getItems().clear();
//        repository.getAllPurchase().stream().filter(p -> p.getDate() != null).forEach(p ->
//                System.out.println(localDateMinimum.isBefore(p.getDate()) + " purchase: " + p.getDate() + ", date filter " + localDateMinimum)
//        );
        repository.getAllPurchase()
                .stream()
                .filter(p -> p.getDate() != null)
                .filter(p -> localDateMinimum.isBefore(p.getDate()))
                .forEach(p -> {
                    addItemsInShoppingList(listOfShoppingObservable, p);
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //листы
        repository = new Repository("repository.json");


        listOfShoppingObservable = FXCollections.observableList(new ArrayList<>());
        shoppingListDateObservable = FXCollections.observableList(new ArrayList<>());
        shoppingListNotDateObservable = FXCollections.observableList(new ArrayList<>());
        shoppingListDateCompletedObservable = FXCollections.observableList(new ArrayList<>());
        shoppingList.setItems(listOfShoppingObservable);
        shoppingListDate.setItems(shoppingListDateObservable);
        shoppingListNotDate.setItems(shoppingListNotDateObservable);
        shoppingListDateCompleted.setItems(shoppingListDateCompletedObservable);

        repository.getAllPurchase().forEach(p -> {
            addItemsInShoppingList(listOfShoppingObservable, p);
        });

        addPurchase.setOnAction(e -> {
            repository.addPurchase(
                    headerPurchaseFiled.getText(),
                    dayNotificationsField.getValue(),
                    priceFiled.getText(),
                    noteFiled.getText()
            );

            listOfShoppingObservable.clear();
            updateList(localDateMinimum);
        });


        //график
        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone", 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet", 36);

        pieChartShopping.getData().add(slice1);
        pieChartShopping.getData().add(slice2);
        pieChartShopping.getData().add(slice3);

        //сортировка radioButton
        ToggleGroup toggleGroup = new ToggleGroup();
        dayButton.setToggleGroup(toggleGroup);
        weekButton.setToggleGroup(toggleGroup);
        monthButton.setToggleGroup(toggleGroup);
        yearButton.setToggleGroup(toggleGroup);

        dayButton.setOnAction(e -> {
            updateList(LocalDate.now().minusDays(1));
        });
        weekButton.setOnAction(e -> {
            updateList(LocalDate.now().minusWeeks(1));
        });
        monthButton.setOnAction(e -> {
            updateList(LocalDate.now().minusMonths(1));
        });
        yearButton.setOnAction(e -> {
            updateList(LocalDate.now().minusYears(1));
        });
    }

    public void addItemsInShoppingList(ObservableList<AnchorPane> list, Purchase purchase) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("purchase.fxml"));
        loader.setControllerFactory(cls -> {
            if (cls == ControllerPurchase.class) {
                return new ControllerPurchase(purchase);
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
