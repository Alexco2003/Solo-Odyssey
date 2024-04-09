package org.example.models;

public class Tank extends Enemy {

    private int armor;

    public Tank(int id_enemy, int id_dungeon, String name, int health, int damage, int armor, String description) {
        super(id_enemy, id_dungeon, name, health, damage, description);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }


}
