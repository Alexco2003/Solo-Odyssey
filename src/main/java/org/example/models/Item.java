package org.example.models;

public class Item {

    private int id_item;
    private int id_shop;
    private String name;
    private String description;
    private double price;
    private int damage;
    private int health;
    private boolean isBought = false;

    public Item(int id_item, int id_shop, String name, String description, double price, int damage, int health, boolean isBought) {
        this.id_item = id_item;
        this.id_shop = id_shop;
        this.name = name;
        this.description = description;
        this.price = price;
        this.damage = damage;
        this.health = health;
        this.isBought = isBought;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean isBought) {
        this.isBought = isBought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_shop() {
        return id_shop;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
