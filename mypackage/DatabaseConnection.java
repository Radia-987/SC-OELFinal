/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A utility class to establish and manage the database connection.
 * Provides methods to connect to the database and execute SQL statements.
 *
 * @author ASUS
 */
public class DatabaseConnection {

    // Database connection and statement objects
    private Connection connection;
    private Statement statement;

    /**
     * Constructor to initialize the database connection.
     * Ensures the MySQL driver is loaded and the connection is established.
     */
    public DatabaseConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql:///todolist", // Database URL (replace with your DB name)
                    "root",                 // Username
                    "radia-987"             // Password
            );

            // Create a statement object for executing SQL queries
            statement = connection.createStatement();
            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    /**
     * Returns the established database connection.
     *
     * @return Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Returns the statement object for executing SQL queries.
     *
     * @return Statement object
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * Closes the database connection and associated resources.
     */
    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
