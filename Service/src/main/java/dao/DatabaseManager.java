package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseManager {
    private final DataSource dataSource;
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "1111";
    private final String databaseName = "my_ticket_service_db";
    @Autowired
    public DatabaseManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        createDatabase();
    }

    private void createDatabase() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE DATABASE " + databaseName);
            System.out.println("Database created successfully (if it didn't exist already).");

        } catch (SQLException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("Database already exists.");
            } else {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        createTables();
    }

    private void createTables() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                String createTypeSQL = "CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');";
                String createUserTableSQL = "CREATE TABLE IF NOT EXISTS \"Client\" (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
                String createTicketTableSQL = "CREATE TABLE IF NOT EXISTS \"Ticket\" (" +
                        "id SERIAL PRIMARY KEY, " +
                        "user_id INTEGER REFERENCES \"Client\"(id) ON DELETE CASCADE, " +
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
