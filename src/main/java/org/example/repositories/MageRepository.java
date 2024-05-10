package org.example.repositories;

import org.example.models.Mage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MageRepository implements GenericRepository<Mage> {
    @Override
    public void add(Mage entity) {

    }

    @Override
    public Mage get(int id) {
        Mage mage = null;
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, m.mana " +
                "FROM Enemy e " +
                "JOIN Mage m ON e.id_enemy = m.id_enemy " +
                "WHERE e.id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();

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
                int mana = rs.getInt("mana");

                mage = new Mage(id_enemy, name, health, damage, mana, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mage;
    }

    @Override
    public ArrayList<Mage> getAll() {
        return null;
    }

    @Override
    public void update(Mage entity) {

    }

    @Override
    public void delete(Mage entity) {

    }
}
