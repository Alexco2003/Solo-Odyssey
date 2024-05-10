package org.example.models;

import java.util.ArrayList;

public class  Shop {

    private int id_shop;
    private int id_player;
    private String name;
    private ArrayList<Item> items;

    public Shop(int id_shop, int id_player, String name, ArrayList<Item> items) {
        this.id_shop = id_shop;
        this.id_player = id_player;
        this.name = name;
        this.items = items;
    }

    public int getId_shop() {
        return id_shop;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shop{id_shop=" + id_shop);
        sb.append(", id_player=" + id_player);
        sb.append(", name=" + name);
        sb.append(", items=" + items + "}\n");
        return sb.toString();
    }
}
