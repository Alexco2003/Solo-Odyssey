package org.example.models;

import java.util.HashSet;

public class Player extends User{
    private int level=1;
    private String title;
    private int damage;
    private int health;
    private double money=0;
    private HashSet<Item> inventory = new HashSet<Item>();


    public Player(int id_user, String username, String password, int level, String title, int damage, int health, double money, HashSet<Item> inventory) {
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

    public HashSet<Item> getInventory() {
        return inventory;
    }

    public void setInventory(HashSet<Item> inventory) {
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
        return "Player{" +
                "id=" + id_user +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", title='" + title + '\'' +
                ", damage=" + damage +
                ", health=" + health +
                ", money=" + money +
                ", inventory=" + inventory +
                '}';
    }








}
