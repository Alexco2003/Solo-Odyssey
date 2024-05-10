package org.example.models;

public class BossAssasinInventory {

        private int id_bossAssasin;
        private int id_item;

        public BossAssasinInventory(int id_bossAssasin, int id_item) {
            this.id_bossAssasin = id_bossAssasin;
            this.id_item = id_item;
        }

        public int getId_bossAssasin() {
            return id_bossAssasin;
        }

        public void setId_bossAssasin(int id_bossAssasin) {
            this.id_bossAssasin = id_bossAssasin;
        }

        public int getId_item() {
            return id_item;
        }

        public void setId_item(int id_item) {
            this.id_item = id_item;
        }

        @Override
        public String toString() {
            return "BossAssasinInventory{" +
                    "id_bossAssasin=" + id_bossAssasin +
                    ", id_item=" + id_item +
                    '}';
        }
}
