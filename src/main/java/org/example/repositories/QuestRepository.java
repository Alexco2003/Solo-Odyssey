package org.example.repositories;

import org.example.models.Quest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestRepository implements GenericRepository<Quest>{


    @Override
    public void add(Quest entity) {

    }

    @Override
    public Quest get(int id) {
        return null;
    }

    @Override
    public ArrayList<Quest> getAll() {
        return null;
    }

    @Override
    public void update(Quest entity) {

    }

    @Override
    public void delete(Quest entity) {

    }

    public ArrayList<Quest> getQuestsByPlayerId(int id) {
        ArrayList<Quest> quests = new ArrayList<>();
        String sql = "SELECT * FROM Quest WHERE id_player = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id_quest = rs.getInt("id_quest");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int rewardLevel = rs.getInt("rewardLevel");
                double rewardMoney = rs.getDouble("rewardMoney");
                boolean completed = rs.getBoolean("completed");
                this.auditDatabase.write(sql, Quest.class, "Done successfully!");
                Quest quest = new Quest(id_quest, id, name, description, rewardLevel, completed, rewardMoney);
                quests.add(quest);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return quests;
    }

    public void completeQuest(int id) {
        String sql = "UPDATE Quest SET completed = true WHERE id_quest = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Quest.class, "Done successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
