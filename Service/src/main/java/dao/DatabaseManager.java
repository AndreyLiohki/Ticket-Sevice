package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "1111";
    private final String databaseName = "my_ticket_service_db";

    public void createDatabase() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                String createDatabaseSQL = "CREATE DATABASE " + databaseName + ";";
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute(createDatabaseSQL);
                    System.out.println("Database created successfully.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(url + databaseName, user, password)) {
            if (connection != null) {
                String createTypeSQL = "CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');";
                String createUserTableSQL = "CREATE TABLE IF NOT EXISTS \"User\" (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
                String createTicketTableSQL = "CREATE TABLE IF NOT EXISTS \"Ticket\" (" +
                        "id SERIAL PRIMARY KEY, " +
                        "user_id INTEGER REFERENCES \"User\"(id) ON DELETE CASCADE, " +
                        "ticket_type ticket_type NOT NULL, " +
                        "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";

                try (Statement stmt = connection.createStatement()) {
                    stmt.execute(createTypeSQL);
                    stmt.execute(createUserTableSQL);
                    stmt.execute(createTicketTableSQL);
                    System.out.println("Tables created successfully.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}