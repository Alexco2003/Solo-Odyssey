package org.example.models;

public class Item {

    private String name;
    private double price;
    private int quantity;
    private int damage;
    private int health;

    public Item(String name, double price, int quantity, int damage, int health) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.damage = damage;
        this.health = health;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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



}
