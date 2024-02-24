package com.example.squarefoot;

public class CustomItem {
     int id;
     String date;
     String length;
     String width;
     String ppu;
     String result;
    // Constructor, getters, and setters
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }

    public String getLength() {
        return length;
    }

    public String getWidth() {
        return width;
    }

    public String getPpu() {
        return ppu;
    }

    public String getResult() {
        return result;
    }

    public CustomItem(String date, String length, String width, String ppu, String result) {
        this.date = date;
        this.length = length;
        this.width = width;
        this.ppu = ppu;
        this.result = result;
    }

    public CustomItem(){}

    public void setId(int id) {
        this.id = id;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setPpu(String ppu) {
        this.ppu = ppu;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

