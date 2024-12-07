package dao;

import model.ticket.Ticket;
import model.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private SessionFactory sessionFactory;
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "1111";
    private final String databaseName = "my_ticket_service_db";

    public void createDatabase(){
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
             statement.executeUpdate("CREATE DATABASE my_ticket_service_db");
            try (Connection dbConnection = DriverManager.getConnection(url + databaseName, user, password);
                 Statement dbStatement = dbConnection.createStatement()) {
                dbStatement.executeUpdate("CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR')");
                dbStatement.executeUpdate(
                        "CREATE TABLE \"Client\" (" +
                                "  id SERIAL PRIMARY KEY," +
                                "  name VARCHAR(100) NOT NULL," +
                                "  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                                ")"
                );

                dbStatement.executeUpdate(
                        "CREATE TABLE Ticket (" +
                                "  id SERIAL PRIMARY KEY," +
                                "  user_id INT NOT NULL," +
                                "  ticket_type ticketTypes NOT NULL," +
                                "  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                                "  FOREIGN KEY (user_id) REFERENCES \"Client\"(id) ON DELETE CASCADE" +
                                ")"
                );
                System.out.println("Database and tables have been created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}