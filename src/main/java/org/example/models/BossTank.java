package org.example.models;

import java.util.ArrayList;

public class BossTank extends Tank{

    private ArrayList<Item> items;

    public BossTank(int id_enemy, int id_dungeon, String name, int health, int damage, int armor, ArrayList<Item> items, String description) {
        super(id_enemy, id_dungeon, name, health, damage, armor, description);
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


}
