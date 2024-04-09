package org.example.models;

public class Tank extends Enemy {

    private int armor;

    public Tank(int id_enemy, int id_dungeon, String name, int health, int damage, int armor) {
        super(id_enemy, id_dungeon, name, health, damage);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }


}
