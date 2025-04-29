package com.example.javaminiproject;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Kivilak Chathuranga
 */


public class MySQLConnection {
    private final String user;
    private final String password;
    private String url;
    private final Connection connection;

    public MySQLConnection() {
        Dotenv dotenv = Dotenv.load();
        this.user = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASSWORD");
        this.url = dotenv.get("DB_URL");

        connection = getConnection();
    }

    private Connection getConnection() { // get connection to the database
        Connection connection = null;
        this.url = this.url + "seaexplorer";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("MySQL ERROR: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Name not found: " + e.getMessage());
        }

        return connection;
    }

}
