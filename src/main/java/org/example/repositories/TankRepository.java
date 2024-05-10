package org.example.repositories;

import org.example.models.Tank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TankRepository implements GenericRepository<Tank> {
    @Override
    public void add(Tank entity) {

    }

    @Override
    public Tank get(int id) {
        Tank tank = null;
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, t.armor " +
                "FROM Enemy e " +
                "JOIN Tank t ON e.id_enemy = t.id_enemy " +
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
                int armor = rs.getInt("armor");

                tank = new Tank(id_enemy, name, health, damage, armor, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tank;
    }

    @Override
    public ArrayList<Tank> getAll() {
        return null;
    }

    @Override
    public void update(Tank entity) {

    }

    @Override
    public void delete(Tank entity) {

    }
}
