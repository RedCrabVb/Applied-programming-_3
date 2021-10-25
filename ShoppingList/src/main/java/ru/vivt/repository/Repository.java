package ru.vivt.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Gson gson = new Gson();
    private String file;

    private static int currentId;
    private List<Purchase> listPurchase;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

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
        sendHttpRequest(purchase);
        save();
    }

    public List<Purchase> getAllPurchase() {
        return listPurchase;
    }

    public void removePurchase(Purchase id) {
        listPurchase.remove(id);
        save();
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

    private void sendHttpRequest(Purchase purchase) {
        try {
            String url = "http://localhost:8080/api?json=" + URLEncoder.encode(gson.toJson(purchase));
            String urlParameters = "";

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(urlParameters))
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

}
