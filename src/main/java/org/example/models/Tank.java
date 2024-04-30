package org.example.models;

public class Tank extends Enemy {

    private int armor;

    public Tank(int id_enemy, String name, int health, int damage, int armor, String description,boolean encountered) {
        super(id_enemy, name, health, damage, description, encountered);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }


}
