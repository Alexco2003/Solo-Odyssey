package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //Change these parameters to your database connection
    private static final String URL = "jdbc:mysql://localhost:3306/soloodyssey?autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "dragon12";

    private static Connection connection = null;
    private static DatabaseConnection instance = null;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void  setInstance(DatabaseConnection Instance) {
        instance = Instance;
    }

    public static void setConnection(Connection Connection) {
        connection = Connection;
    }



}
