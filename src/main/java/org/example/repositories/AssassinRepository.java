package org.example.repositories;

import org.example.models.Assassin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssassinRepository implements GenericRepository<Assassin> {



    @Override
    public void add(Assassin entity) {

    }

    @Override
    public Assassin get(int id) {
        Assassin assassin = null;
        Connection conn = this.databaseConnection.getConnection();
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, a.criticalChance " +
                "FROM Enemy e " +
                "JOIN Assassin a ON e.id_enemy = a.id_enemy " +
                "WHERE e.id_enemy = ?";

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id_enemy = rs.getInt("id_enemy");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int health = rs.getInt("health");
                int damage = rs.getInt("damage");
                boolean encountered = rs.getBoolean("encountered");
                int criticalChance = rs.getInt("criticalChance");

                assassin = new Assassin(id_enemy, name, health, damage, criticalChance, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return assassin;
    }

    @Override
    public ArrayList<Assassin> getAll() {
        return null;
    }

    @Override
    public void update(Assassin entity) {

    }

    @Override
    public void delete(Assassin entity) {

    }
}
