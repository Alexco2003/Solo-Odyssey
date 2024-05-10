package org.example.models;

public class BossMageInventory {

    private int id_bossMage;
    private int id_item;

    public BossMageInventory(int id_bossMage, int id_item) {
        this.id_bossMage = id_bossMage;
        this.id_item = id_item;
    }

    public int getId_bossMage() {
        return id_bossMage;
    }

    public void setId_bossMage(int id_bossMage) {
        this.id_bossMage = id_bossMage;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    @Override
    public String toString() {
        return "BossMageInventory{" +
                "id_bossMage=" + id_bossMage +
                ", id_item=" + id_item +
                '}';
    }
}
