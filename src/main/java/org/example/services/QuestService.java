package org.example.services;

import org.example.models.Quest;
import org.example.repositories.QuestRepository;

import java.util.ArrayList;

public class QuestService {

    private QuestRepository questRepository;

    public QuestService() {
        this.questRepository = new QuestRepository();
    }

    public ArrayList<Quest> getQuestsByPlayerId(int playerId) {
        return questRepository.getQuestsByPlayerId(playerId);
    }

    public void completeQuest(int id) {
        questRepository.completeQuest(id);
    }
}
