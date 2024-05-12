package org.example.models;

import java.util.ArrayList;

public class BossMage extends Mage{

    private ArrayList<Item> items;

    public BossMage(int id_enemy, String name, int health, int damage, int mana, ArrayList<Item> items, String description, boolean encountered) {
        super(id_enemy, name, health, damage, mana, description, encountered);
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
        sb.append("Inventory:" + "\n");
        for (Item item : items) {
            sb.append(item.toString3());
        }
        return sb.toString();
    }


}
