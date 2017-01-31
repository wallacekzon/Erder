package com.example.walla.erder;

/**
 * Created by zhang4all on 1/25/2017.
 */

public class MyMenuItem {
    private String itemName;
    private String itemDescription;
    private double itemPriceDouble;
    private String itemPriceString;

    public MyMenuItem(String name, String description, double price) {
        itemName = name;
        itemDescription = description;
        itemPriceDouble = price;
        itemPriceString = "$" + Double.toString(itemPriceDouble);
    }

    String getItemName() {
        return itemName;
    }

    String getItemDescription() {
        return itemDescription;
    }

    String getItemPriceString() {
        return itemPriceString;
    }
}
