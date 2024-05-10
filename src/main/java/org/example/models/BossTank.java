package org.example.models;

import java.util.ArrayList;

public class BossTank extends Tank{

    private ArrayList<Item> items;

    public BossTank(int id_enemy, String name, int health, int damage, int armor, ArrayList<Item> items, String description, boolean encountered) {
        super(id_enemy, name, health, damage, armor, description, encountered);
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("items=" + items + "\n");
        return sb.toString();
    }


}
