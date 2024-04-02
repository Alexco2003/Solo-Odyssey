package org.example.models;

import java.util.ArrayList;

public class Player extends User{
    private int level;
    private int damage;
    private int health;
    private ArrayList<Item> inventory;

    public Player(String username, String password, int level, int damage, int health, ArrayList<Item> inventory) {
        super(username, password);
        this.level = level;
        this.damage = damage;
        this.health = health;
        this.inventory = inventory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }







}
