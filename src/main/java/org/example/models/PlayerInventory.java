package org.example.models;

public class PlayerInventory {

    private int id_item;
    private int id_player;

    public PlayerInventory(int id_item, int id_player) {
        this.id_item = id_item;
        this.id_player = id_player;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }


}
