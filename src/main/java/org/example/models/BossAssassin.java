package org.example.models;

import java.util.ArrayList;

public class BossAssassin extends Assassin{

    private ArrayList<Item> items;


    public BossAssassin(int id_enemy, int id_dungeon, String name, int health, int damage, int criticalChance, ArrayList<Item> items, String description) {
        super(id_enemy, id_dungeon, name, health, damage, criticalChance, description);
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


}
