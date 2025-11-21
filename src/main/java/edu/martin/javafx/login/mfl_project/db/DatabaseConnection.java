package edu.martin.javafx.login.mfl_project.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mfl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (ClassNotFoundException e) {
                    System.err.println("Error: MySQL JDBC Driver not found.");
                    e.printStackTrace();
                }
            }
            return connection;
        }
    }
