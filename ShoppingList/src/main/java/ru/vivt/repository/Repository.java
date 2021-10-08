package ru.vivt.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Gson gson = new Gson();
    private String file;

    private static int currentId;
    private JsonArray jsonArray;

    public Repository(String file) {
        this.file = file;
        try {
            jsonArray = JsonParser.parseReader(new FileReader(new File(file))).getAsJsonArray();
            jsonArray.forEach(r -> {
                int id = r.getAsJsonObject().get("id").getAsInt();
                if (id > currentId) {
                    currentId = id;
                }
            });
        } catch (FileNotFoundException | IllegalStateException e) {
            jsonArray = new JsonArray();
            currentId = 1;
            save();
        }
    }

    public void addPurchase(String text, String date) {
        Purchase purchase = new Purchase(currentId++, text, date);
        jsonArray.add(JsonParser.parseString(gson.toJson(purchase, Purchase.class)));
    }

    public List<Purchase> getAll() {
        List<Purchase> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject json = jsonArray.get(i).getAsJsonObject();
            list.add(gson.fromJson(json, Purchase.class));
        }
        return list;
    }

    public void removePurchase(int id) {
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i).getAsJsonObject().get("id").getAsInt() == id) {
                jsonArray.remove(i);
                break;
            }
        }
    }

    public void completedPurchase(int id, boolean completed) {
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i).getAsJsonObject().get("id").getAsInt() == id) {
//                jsonArray.get(i).getAsJsonObject().remove("")
                break;
            }
        }
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(new File(file));
            fileWriter.write(jsonArray.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
