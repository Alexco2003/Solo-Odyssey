package org.example.models;

import java.util.ArrayList;

public class Dungeon {
    private int id_dungeon;
    private int id_player;
    private String name;
    private String description;
    private int level;
    private int rewardLevel;
    private double rewardMoney;
    private boolean completed = false;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Dungeon(int id_dungeon, int id_player, String name, String description, int level, int rewardLevel, boolean completed, ArrayList<Enemy> enemies, double rewardMoney) {
        this.id_dungeon = id_dungeon;
        this.id_player = id_player;
        this.name = name;
        this.description = description;
        this.level = level;
        this.rewardLevel = rewardLevel;
        this.completed = completed;
        this.enemies = enemies;
        this.rewardMoney = rewardMoney;
    }

    public double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public int getId_dungeon() {
        return id_dungeon;
    }

    public void setId_dungeon(int id_dungeon) {
        this.id_dungeon = id_dungeon;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(int rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }






}
