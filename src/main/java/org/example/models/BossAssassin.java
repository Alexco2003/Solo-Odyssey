package org.example.models;

import java.util.ArrayList;

public class BossAssassin extends Assassin{

    private ArrayList<Item> items;


    public BossAssassin(int id_enemy, String name, int health, int damage, int criticalChance, ArrayList<Item> items, String description, boolean encountered) {
        super(id_enemy, name, health, damage, criticalChance, description, encountered);
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


}
