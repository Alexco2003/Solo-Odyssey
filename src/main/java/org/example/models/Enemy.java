package org.example.models;

public abstract class Enemy {
    private int id_enemy;
    private String name;
    private String description;
    private int health;
    private int damage;
    private boolean encountered = false;

    public Enemy(int id_enemy, String name, int health, int damage, String description, boolean encountered) {
        this.id_enemy = id_enemy;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.description = description;
        this.encountered = encountered;
    }

    public boolean isEncountered() {
        return encountered;
    }

    public void setEncountered(boolean encountered) {
        this.encountered = encountered;
    }

    public int getId_enemy() {
        return id_enemy;
    }

    public void setId_enemy(int id_enemy) {
        this.id_enemy = id_enemy;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<<-- Enemy " + id_enemy + " -->>\n");
        sb.append("name='" + name + "'\n");
        sb.append("health=" + health + "\n");
        sb.append("damage=" + damage + "\n");
        sb.append("description='" + description + "'\n");
        sb.append("encountered=" + encountered + "\n");
        return sb.toString();
    }
}
