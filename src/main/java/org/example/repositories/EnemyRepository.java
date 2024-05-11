package org.example.repositories;

import org.example.models.Enemy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnemyRepository implements GenericRepository<Enemy>{

    @Override
    public void add(Enemy entity) {

    }

    @Override
    public Enemy get(int id) {
        return null;
    }

    @Override
    public ArrayList<Enemy> getAll() {
        return null;
    }

    @Override
    public void update(Enemy entity) {

    }

    @Override
    public void delete(Enemy entity) {

    }

    public void updateEnemyEncountered(int id) {
        String sql = "UPDATE Enemy SET encountered = true WHERE id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Enemy.class, "Done successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
}
