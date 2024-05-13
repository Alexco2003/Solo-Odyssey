package org.example.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Player extends User{
    private int level=1;
    private String title;
    private int damage;
    private int health;
    private double money=0;
    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();


    public Player(int id_user, String username, String password, int level, String title, int damage, int health, double money, HashMap<Item, Integer> inventory) {
        super(id_user,username, password);
        this.level = level;
        this.title = title;
        this.damage = damage;
        this.health = health;
        this.inventory = inventory;
        this.money = money;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Item, Integer> inventory) {
        this.inventory = inventory;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<<-- Player " + id_user + " -->>\n");
        sb.append("Username = '" + username + "'\n");
        sb.append("Password = '" + password + "'\n");
        sb.append("Level = " + level + "\n");
        sb.append("Title = '" + title + "'\n");
        sb.append("Damage = " + damage + "\n");
        sb.append("Health = " + health + "\n");
        sb.append("Money = " + money + "\n");
        sb.append("Inventory:\n");
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            sb.append(entry.getKey().getName() + ": " + entry.getValue() + "\n");
        }

        return sb.toString();
    }

    public String toString2()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<<-- Player " + id_user + ", " + username + " : " + title + " -->>\n");
        sb.append("Level = " + level + "\n");
        return sb.toString();
    }

    public String toString3()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<<-- Player " + id_user + ", " + username + " : " + title + " -->>\n");
        sb.append("Level = " + level + "\n");
        sb.append("Damage = " + damage + "\n");
        sb.append("Health = " + health + "\n");
        return sb.toString();
    }








}
