package com.example.walla.erder;


import android.media.Image;

/**
 * Created by walla on 12/24/2016.
 */
//test

public class Restaurant {
    private long restaurantID;
    private String name;
    private String phone;
    private String address;

    private RestaurantMenu[] restaurantMenus;
    private Image restaurantImage;


    // eventually more such as reviews, ratings, restaurant types, price range, etc.

    // constructors
    public Restaurant(long restaurantID, String name, String phone, String address) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenus = null;
        this.restaurantImage = null;
    }
    public Restaurant(long restaurantID, String name, String phone, String address,
                      RestaurantMenu[] restaurantMenus) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenus = restaurantMenus;
    }
    public Restaurant(long restaurantID, String name, String phone, String address,
                      RestaurantMenu[] restaurantMenus, Image restaurantImage) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.restaurantMenus = restaurantMenus;
        this.restaurantImage = restaurantImage;
    }



    // getters
    public long getRestaurantID() {
        return restaurantID;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

    public RestaurantMenu[] getRestaurantMenus() {
        return restaurantMenus;
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
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setRestaurantMenus(RestaurantMenu[] restaurantMenus) {
        this.restaurantMenus = restaurantMenus;
    }
    public void setRestaurantImage(Image restaurantImage) {
        this.restaurantImage = restaurantImage;
    }
}