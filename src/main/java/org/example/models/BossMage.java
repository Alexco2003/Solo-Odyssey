package org.example.models;

import java.util.ArrayList;

public class BossMage extends Mage{

    private ArrayList<Item> items;

    public BossMage(int id_enemy, int id_dungeon, String name, int health, int damage, int mana, ArrayList<Item> items, String description) {
        super(id_enemy, id_dungeon, name, health, damage, mana, description);
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


}
