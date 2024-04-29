package org.example.models;

public class DungeonEnemy {

    public int id_dungeon;
    public int id_enemy;

    public DungeonEnemy(int id_dungeon, int id_enemy) {
        this.id_dungeon = id_dungeon;
        this.id_enemy = id_enemy;
    }

    public int getId_dungeon() {
        return id_dungeon;
    }

    public void setId_dungeon(int id_dungeon) {
        this.id_dungeon = id_dungeon;
    }

    public int getId_enemy() {
        return id_enemy;
    }

    public void setId_enemy(int id_enemy) {
        this.id_enemy = id_enemy;
    }

}
