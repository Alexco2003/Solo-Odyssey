package org.example.models;

public abstract class Enemy {
    private int id_enemy;
    private int id_dungeon;
    private String name;
    private String description;
    private int health;
    private int damage;

    public Enemy(int id_enemy, int id_dungeon, String name, int health, int damage, String description) {
        this.id_enemy = id_enemy;
        this.id_dungeon = id_dungeon;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.description = description;
    }

    public int getId_dungeon() {
        return id_dungeon;
    }

    public void setId_dungeon(int id_dungeon) {
        this.id_dungeon = id_dungeon;
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
}
