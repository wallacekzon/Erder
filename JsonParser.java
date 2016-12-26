package com.example.walla.erder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by walla on 12/25/2016.
 */

public class JsonParser {
    private Restaurant restaurant;
    private JSONObject jsonObject;

    public JsonParser(Restaurant restaurant, String jsonString) {
        this.restaurant = restaurant;
        try {
            this. jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createRestaurantProfile();
        printRestaurantProfile();
    }

    private void printRestaurantProfile() {
        System.out.println(restaurant.toString());
        System.out.println(restaurant.getRestaurantMenus()[0].toString());

        for (MenuCategory c : restaurant.getRestaurantMenus()[0].getMenuCategories()) {
            System.out.println(c.toString());
            for (MenuDish d : c.getMenuDishes()) {
                System.out.println(d.toString());
            }
        }
    }

    private void createRestaurantProfile() {
        try {
            JSONObject restaurantInfo = jsonObject.getJSONObject("restaurantInfo");
            long restaurantID = restaurantInfo.getLong("RestaurantID");
            String restaurantName = restaurantInfo.getString("Name");
            String restaurantPhone = restaurantInfo.getString("Phone");
            String restaurantAddress = restaurantInfo.getString("Address");
            restaurant.setRestaurantID(restaurantID);
            restaurant.setName(restaurantName);
            restaurant.setPhone(restaurantPhone);
            restaurant.setAddress(restaurantAddress);

            RestaurantMenu restaurantMenu[] = createRestaurantMenu();
            restaurant.setRestaurantMenus(restaurantMenu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private RestaurantMenu[] createRestaurantMenu() {
        RestaurantMenu restaurantMenu = new RestaurantMenu();

        try {
            JSONArray dishes = jsonObject.getJSONArray("dishes");

            long menuID = dishes.getJSONObject(0).getLong("MenuID");
            restaurantMenu.setMenuID(menuID);
            restaurantMenu.setMenuCategories(createMenuCategoriesAndDishes(dishes));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        RestaurantMenu[] restaurantMenus = new RestaurantMenu[] {restaurantMenu};
        return restaurantMenus;
    }

    private MenuCategory[] createMenuCategoriesAndDishes(JSONArray dishItems) {
        ArrayList<MenuCategory> menuCategoryArrayList = new ArrayList<>();
        ArrayList<MenuDish> menuDishArrayList = new ArrayList<>();

        try {
            int numDishes = dishItems.length();
            JSONObject oneDish = dishItems.getJSONObject(0);

            long categoryID = oneDish.getLong("CategoryID");
            String categoryName = oneDish.getString("CategoryName");
            long dishID = -1;
            String dishName = null;
            double dishPrice = -1;

            MenuCategory newCategory = null;
            MenuDish newDish = null;

            MenuDish[] menuDishes = null;

            for (int i = 0; i < numDishes; i++) {
                oneDish = dishItems.getJSONObject(i);

                dishID = oneDish.getLong("DishID");
                dishName = oneDish.getString("DishName");
                dishPrice = oneDish.getDouble("DishPrice");

                newDish = new MenuDish(dishID,dishName, dishPrice);
                menuDishArrayList.add(newDish);

                if (oneDish.getLong("CategoryID") != categoryID) {
                    menuDishes = createMenuDishesFromArrayList(menuDishArrayList);

                    newCategory = new MenuCategory(categoryID, categoryName);
                    newCategory.setMenuDishes(menuDishes);
                    menuCategoryArrayList.add(newCategory);

                    menuDishArrayList = new ArrayList<>();

                    categoryID = oneDish.getLong("CategoryID");
                    categoryName = oneDish.getString("CategoryName");
                }
            }

            menuDishes = createMenuDishesFromArrayList(menuDishArrayList);

            newCategory = new MenuCategory(categoryID, categoryName);
            newCategory.setMenuDishes(menuDishes);
            menuCategoryArrayList.add(newCategory);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        MenuCategory[] menuCategories = createMenuCategoriesFromArrayList(menuCategoryArrayList);
        return menuCategories;
    }

    private MenuCategory[] createMenuCategoriesFromArrayList(ArrayList<MenuCategory> arrayList) {
        int numCategories = arrayList.size();

        MenuCategory[] menuCategories = new MenuCategory[numCategories];

        int counter = 0;
        for (MenuCategory oneCategory : arrayList) {
            menuCategories[counter] = oneCategory;
        }

        return menuCategories;
    }

    private MenuDish[] createMenuDishesFromArrayList(ArrayList<MenuDish> arrayList) {
        int numDishes = arrayList.size();

        MenuDish[] menuDishes = new MenuDish[numDishes];

        int counter = 0;
        for (MenuDish oneDish : arrayList) {
            menuDishes[counter] = oneDish;
        }

        return menuDishes;
    }
}
