package com.example.walla.erder;

/**
 * Created by walla on 12/25/2016.
 */

public class Constants {
    private Constants() {}

    public static final String GET_MENU_FOLLOWED_BY_CURRENT_LOCATION = "";

    public static final String GET_MENUITEM_CATEGORY_NAME_PRICE_BY_ADDRESS =
            "SELECT categories.Name AS Category, dishes.Name AS Name, dishes.Price AS Price " +
                    "FROM restaurants " +
                    "INNER JOIN restaurantmenus ON " +
                    "restaurantmenus.RestaurantID = restaurants.RestaurantID " +
                    "INNER JOIN menucategories ON " +
                    "menucategories.MenuID = restaurantmenus.MenuID " +
                    "INNER JOIN categories ON " +
                    "categories.CategoryID = menucategories.CategoryID " +
                    "INNER JOIN categorydishes ON " +
                    "categorydishes.CategoryID = categories.CategoryID " +
                    "INNER JOIN dishes ON " +
                    "dishes.DishID = categorydishes.DishID " +
                    "WHERE address = $address" +
                    "ORDER BY categories.Name, dishes.Name";
}
