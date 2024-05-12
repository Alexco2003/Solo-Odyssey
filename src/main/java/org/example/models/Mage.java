package org.example.models;

public class Mage extends Enemy {

        private int mana;

        public Mage(int id_enemy, String name, int health, int damage, int mana, String description, boolean encountered) {
            super(id_enemy, name, health, damage, description, encountered);
            this.mana = mana;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(super.toString());
            sb.append("Mana = " + mana + "\n");
            return sb.toString();
        }
}
