package org.example.models;

public class BossTankInventory {

    private int id_bossTank;
    private int id_item;

    public BossTankInventory(int id_bossTank, int id_item) {
        this.id_bossTank = id_bossTank;
        this.id_item = id_item;
    }

    public int getId_bossTank() {
        return id_bossTank;
    }

    public void setId_bossTank(int id_bossTank) {
        this.id_bossTank = id_bossTank;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }
}
