package org.example.models;

import java.util.ArrayList;

public class Shop {

    private int id_shop;
    private int id_player;
    private String name;
    private ArrayList<Item> items;

    public Shop(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
