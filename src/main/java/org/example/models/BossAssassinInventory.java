package org.example.models;

public class BossAssassinInventory {

        private int id_bossAssassin;
        private int id_item;

        public BossAssassinInventory(int id_bossAssassin, int id_item) {
            this.id_bossAssassin = id_bossAssassin;
            this.id_item = id_item;
        }

        public int getid_bossAssassin() {
            return id_bossAssassin;
        }

        public void setid_bossAssassin(int id_bossAssassin) {
            this.id_bossAssassin = id_bossAssassin;
        }

        public int getId_item() {
            return id_item;
        }

        public void setId_item(int id_item) {
            this.id_item = id_item;
        }

        @Override
        public String toString() {
            return "BossAssassinInventory{" +
                    "id_bossAssassin=" + id_bossAssassin +
                    ", id_item=" + id_item +
                    '}';
        }
}
