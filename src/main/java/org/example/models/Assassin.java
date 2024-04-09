package org.example.models;

public class Assassin extends Enemy{

    private int criticalChance;

    public Assassin(int id_enemy, int id_dungeon, String name, int health, int damage, int criticalChance, String description) {
        super(id_enemy, id_dungeon, name, health, damage, description);
        this.criticalChance = criticalChance;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }
}
