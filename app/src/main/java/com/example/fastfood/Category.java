package com.example.fastfood;

public class Category {
    private String name;
    private int imageResource;
    private String price;

    public Category(String name, int imageResource, String price) {
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getPrice() {
        return price;
    }
} 