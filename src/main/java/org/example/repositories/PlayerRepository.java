package org.example.repositories;

import org.example.models.Player;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class PlayerRepository implements GenericRepository<Player> {

    public PlayerRepository() {
    }


    @Override
    public void add(Player player) {
        int userId = -1;
        String sql1 = "INSERT INTO USER (username, password) VALUES (?, ?)";
        Connection conn = this.databaseConnection.getConnection();

        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, player.getUsername());
            pstmt.setString(2, player.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
            this.auditDatabase.write(sql1, User.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "INSERT INTO Player (id_user, level, title, damage, health, money) VALUES (?, ?, ?, ?, ?, ?)";

        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, player.getLevel());
            pstmt.setString(3, player.getTitle());
            pstmt.setInt(4, player.getDamage());
            pstmt.setInt(5, player.getHealth());
            pstmt.setDouble(6, player.getMoney());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, player, "Done successfully!");

            player.setId_user(userId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public Player get(int id) {
        String sql = "SELECT * FROM Player INNER JOIN User ON Player.id_user = User.id_user WHERE Player.id_user = ?";
        Connection conn = this.databaseConnection.getConnection();
        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Player.class, "Done successfully!");

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int level = rs.getInt("level");
                String title = rs.getString("title");
                int damage = rs.getInt("damage");
                int health = rs.getInt("health");
                double money = rs.getDouble("money");
                return new Player(id, username, password, level, title, damage, health, money, new HashSet<>());
                //TODO: get inventory
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Player> getAll() {
        String sql = "SELECT * FROM Player INNER JOIN User ON Player.id_user = User.id_user";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Player.class, "Done successfully!");

            ArrayList<Player> players = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int level = rs.getInt("level");
                String title = rs.getString("title");
                int damage = rs.getInt("damage");
                int health = rs.getInt("health");
                double money = rs.getDouble("money");
                players.add(new Player(id, username, password, level, title, damage, health, money, new HashSet<>()));
                //TODO: get inventory
            }

            return players;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Player player) {
        String sql = "UPDATE Player SET level = ?, title = ?, damage = ?, health = ?, money = ? WHERE id_user = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, player.getLevel());
            pstmt.setString(2, player.getTitle());
            pstmt.setInt(3, player.getDamage());
            pstmt.setInt(4, player.getHealth());
            pstmt.setDouble(5, player.getMoney());
            pstmt.setInt(6, player.getId_user());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, player, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Player player) {
        String sql = "DELETE FROM Player WHERE id_user = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, player.getId_user());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, player, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
