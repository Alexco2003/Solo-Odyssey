package org.example.models;

public class Mage extends Enemy {

        private int mana;

        public Mage(int id_enemy, int id_dungeon, String name, int health, int damage, int mana, String description) {
            super(id_enemy, id_dungeon, name, health, damage, description);
            this.mana = mana;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }
}
