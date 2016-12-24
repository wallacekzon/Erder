package com.example.walla.erder;


import android.media.Image;

/**
 * Created by walla on 12/24/2016.
 */

public class Restaurant {
    private long restaurantID;
    private String name;
    private int phone;
    private String address;

    private RestaurantMenu restaurantMenu;
    private Image restaurantImage;


    // eventually more such as reviews, ratings, restaurant types, price range, etc.

    // constructors
    public Restaurant(long restaurantID, String name, int phone, String address) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenu = null;
        this.restaurantImage = null;
    }
    public Restaurant(long restaurantID, String name, int phone, String address,
                      RestaurantMenu restaurantMenu) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenu = restaurantMenu;
    }
    public Restaurant(long restaurantID, String name, int phone, String address,
                      RestaurantMenu restaurantMenu, Image restaurantImage) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenu = restaurantMenu;
        this.restaurantImage = restaurantImage;
    }



    // getters
    public long getRestaurantID() {
        return restaurantID;
    }
    public String getName() {
        return name;
    }
    public int getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

    public Image getRestaurantImage() {
        return restaurantImage;
    }

    // setters
    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setRestaurantImage(Image restaurantImage) {
        this.restaurantImage = restaurantImage;
    }
}
