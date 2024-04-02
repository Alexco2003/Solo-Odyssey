package org.example.models;

import java.util.HashMap;

public class Dungeon {
    private String name;
    private int level;
    private int reward;
    private HashMap<Enemy, Integer> enemies;

    public Dungeon(String name, int level, int reward, HashMap<Enemy, Integer> enemies) {
        this.name = name;
        this.level = level;
        this.reward = reward;
        this.enemies = enemies;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public HashMap<Enemy, Integer> getEnemies() {
        return enemies;
    }

    public void setEnemies(HashMap<Enemy, Integer> enemies) {
        this.enemies = enemies;
    }
}
