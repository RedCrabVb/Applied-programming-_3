package ru.vivt.repository;

import java.time.LocalDate;

public class Purchase {
    private int id;
    private String header;
    private LocalDate date;
    private boolean completed;

    private String price;
    private String note;
    private String category;

    public Purchase(int id, String header, LocalDate date) {
        this.id = id;
        this.header = header;
        this.date = date;
        this.completed = false;
    }

    protected Purchase() {

    }


    public void setNote(String note) {
        this.note = note;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getHeader() {
        return header;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
