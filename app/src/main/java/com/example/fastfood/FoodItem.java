package com.example.fastfood;

public class FoodItem {
    private String name;
    private String price;
    private int imageResource;
    private float rating;
    private boolean isSelected;

    public FoodItem(String name, String price, int imageResource, float rating) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.rating = rating;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public float getRating() {
        return rating;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
} 