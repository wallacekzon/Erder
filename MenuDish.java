package com.example.walla.erder;

import android.media.Image;

/**
 * Created by walla on 12/24/2016.
 */

public class MenuDish {
    // required information
    private long dishID;
    private String name;
    private double price;

    // additional information
    private Image dishImage;
    private String description;
    private boolean vegetarian;
    private boolean spicy;

    //constructors
    public MenuDish(long dishID, String name, double price) {
        this.dishID = dishID;
        this.name = name;
        this.price = price;

        this.dishImage = null;
        this.description = null;
        this.vegetarian = false;
        this.spicy = false;
    }
    public MenuDish(long dishID, String name, double price,
                    Image dishImage, String description, boolean vegetarian, boolean spicy) {
        this.dishID = dishID;
        this.name = name;
        this.price = price;

        this.dishImage = dishImage;
        this.description = description;
        this.vegetarian = vegetarian;
        this.spicy = spicy;
    }

    //getters
    public long getDishID(){ return dishID; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }

    public Image getDishImage() { return dishImage; }
    public String getDescription(){ return description; }
    public boolean isVegetarian() { return vegetarian; }
    public boolean isSpicy() { return spicy; }

    //setters
    public void setDishID(long dishID){ this.dishID = dishID; }
    public void setName(String name){ this.name = name; }
    public void setPrice(double price){ this.price = price; }

    public void setDishImage(Image dishImage) { this.dishImage = dishImage; }
    public void setDescription(String description){ this.description = description; }
    public void setVegetarian(boolean vegetarian) { this.vegetarian = vegetarian; }
    public void setSpicy(boolean spicy) { this.spicy = spicy; }

    public String toString() {
        return new String (dishID + name + price);
    }
}
