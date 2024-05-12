package org.example.repositories;

import org.example.models.Dungeon;
import org.example.models.DungeonEnemy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DungeonRepository implements GenericRepository<Dungeon> {
    @Override
    public void add(Dungeon entity) {
    }

    @Override
    public Dungeon get(int id) {
        return null;
    }

    @Override
    public ArrayList<Dungeon> getAll() {
        return null;
    }

    @Override
    public void update(Dungeon entity) {

    }

    @Override
    public void delete(Dungeon entity) {

    }

    public ArrayList<Dungeon> getDungeonByPlayerId(int id) {
        ArrayList<Dungeon> dungeons = new ArrayList<>();
        String sql = "SELECT * FROM Dungeon WHERE id_player = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id_dungeon = rs.getInt("id_dungeon");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int level = rs.getInt("level");
                int rewardLevel = rs.getInt("rewardLevel");
                double rewardMoney = rs.getDouble("rewardMoney");
                boolean completed = rs.getBoolean("completed");
                this.auditDatabase.write(sql, Dungeon.class, "Done successfully!");
                Dungeon dungeon = new Dungeon(id_dungeon, id, name, description, level, rewardLevel, completed, null, rewardMoney);
                dungeons.add(dungeon);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dungeons;
    }

    public ArrayList<Integer> getEnemiesByDungeonId(int id) {
        ArrayList<Integer> enemies = new ArrayList<>();
        String sql = "SELECT id_enemy FROM DungeonEnemy WHERE id_dungeon = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id_enemy = rs.getInt("id_enemy");
                enemies.add(id_enemy);
            }

            this.auditDatabase.write(sql, DungeonEnemy.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return enemies;
    }

    public void updateDungeonCompleted(int id) {
        String sql = "UPDATE Dungeon SET completed = true WHERE id_dungeon = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            this.auditDatabase.write(sql, Dungeon.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int countDungeonsCompleted(int playerId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Dungeon WHERE id_player = ? AND completed = true";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            this.auditDatabase.write(sql, Dungeon.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }


}
