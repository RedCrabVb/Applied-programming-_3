package ru.vivt.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Gson gson = new Gson();
    private String file;

    private static int currentId;
    private List<Purchase> listPurchase;

    public Repository(String file) {
        this.file = file;
        this.listPurchase = new ArrayList<>();
        try {
            JsonArray jsonArray = JsonParser.parseReader(new FileReader(file)).getAsJsonArray();
            jsonArray.forEach(p -> {
                listPurchase.add(gson.fromJson(p, Purchase.class));
            });
        } catch (FileNotFoundException | IllegalStateException e) {
            currentId = 1;
            save();
        }
    }

    public void addPurchase(String header, LocalDate date, Integer price, String note, String category) {
        Purchase purchase = new Purchase(currentId++, header, date);
        purchase.setPrice(price);
        purchase.setNote(note);
        purchase.setCategory(category);

        listPurchase.add(purchase);
        save();
    }

    public List<Purchase> getAllPurchase() {
        return listPurchase;
    }

    public void removePurchase(int id) {
        for (int i = 0; i < listPurchase.size(); i++) {
            if (listPurchase.get(i).getId() == id) {
                listPurchase.remove(i);
                return;
            }
        }
    }

    public void completedPurchase(int id, boolean completed) {
        listPurchase.stream().filter(r -> r.getId() == id).findAny().get().setCompleted(completed);
    }

    public void save() {
        try {
            JsonArray jsonArray = new JsonArray();
            for (Purchase p : listPurchase) {
                jsonArray.add(gson.toJsonTree(p));
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
