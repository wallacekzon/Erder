package com.example.walla.erder;

/**
 * Created by walla on 12/24/2016.
 */

public class MenuCategory {
    private long categoryID;
    private String categoryName;

    private MenuDish[] menuDishes;


    public MenuCategory(long categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    public MenuCategory(long categoryID, String categoryName, MenuDish[] menuDishes) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;

        this.menuDishes = menuDishes;
    }

    // getters
    public long getCategoryID() {
        return categoryID;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public MenuDish[] getMenuDishes() {
        return menuDishes;
    }

    // setters
    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public void setMenuDishes(MenuDish[] menuDishes) {
        this.menuDishes = menuDishes;
    }

    public String toString() {
        return new String(categoryID + categoryName);
    }
}
