package org.example.models;

public class Assassin extends Enemy{

    private int criticalChance;

    public Assassin(int id_enemy, String name, int health, int damage, int criticalChance, String description, boolean encountered) {
        super(id_enemy, name, health, damage, description, encountered);
        this.criticalChance = criticalChance;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }
}
