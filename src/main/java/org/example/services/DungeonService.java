package org.example.services;

import org.example.models.Dungeon;
import org.example.repositories.DungeonRepository;

import java.util.ArrayList;

public class DungeonService {

    private DungeonRepository dungeonRepository;

    public DungeonService() {
        this.dungeonRepository = new DungeonRepository();
    }

    public ArrayList<Dungeon> getDungeonByPlayerId(int id) {
        return dungeonRepository.getDungeonByPlayerId(id);
    }

    public ArrayList<Integer> getEnemiesByDungeonId(int id) {
        return dungeonRepository.getEnemiesByDungeonId(id);
    }

    public void completeDungeon(int id) {
        dungeonRepository.updateDungeonCompleted(id);
    }

    public int countDungeonsCompleted(int playerId)
    {
        return dungeonRepository.countDungeonsCompleted(playerId);
    }


}
