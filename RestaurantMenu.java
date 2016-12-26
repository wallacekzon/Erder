package com.example.walla.erder;

/**
 * Created by walla on 12/24/2016.
 */

public class RestaurantMenu {
    private long menuID;

    private MenuCategory[] menuCategories;

    // constructors
    public RestaurantMenu() {
        this.menuID = 0;
        this.menuCategories = null;
    }
    public RestaurantMenu(long menuID) {
        this.menuID = menuID;
        this.menuCategories = null;
    }
    public RestaurantMenu(long menuID, MenuCategory[] menuCategories) {
        this.menuID = menuID;
        this.menuCategories = menuCategories;
    }

    // getters
    public long getMenuID() {
        return menuID;
    }
    public MenuCategory[] getMenuCategories() {
        return menuCategories;
    }

    // setters
    public void setMenuID(long menuID) {
        this.menuID = menuID;
    }
    public void setMenuCategories(MenuCategory[] menuCategories) {
        this.menuCategories = menuCategories;
    }

    public String toString() {
        return String.valueOf(menuID);
    }
}