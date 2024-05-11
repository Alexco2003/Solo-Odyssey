package org.example.repositories;

import org.example.models.Quest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {

    private QuestRepository questRepository = new QuestRepository();

    @Test
    void getQuestsByPlayerId() {
        assertNotNull(questRepository.getQuestsByPlayerId(3));
        System.out.println();
        ArrayList<Quest> quests = questRepository.getQuestsByPlayerId(3);
        for (Quest quest : quests) {
            System.out.println(quest);
        }
    }

    @Test
    void completeQuest() {
        questRepository.completeQuest(63);
    }
}