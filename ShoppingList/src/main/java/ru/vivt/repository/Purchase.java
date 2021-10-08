package ru.vivt.repository;

public class Purchase {
    private int id;
    private String header;
    private String date;
    private boolean completed;

    private String note;

    public Purchase(int id, String header, String date) {
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

    public String getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }
}
