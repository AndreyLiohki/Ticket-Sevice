package dao;

import model.ticket.Ticket;
import model.ticket.ticketTypes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private final String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private final String user = "postgres";
    private final String password = "1111";

    public void saveTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO \"Ticket\" (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ticket.getTicketId());
            pstmt.setInt(2, ticket.getTicketUserId());
            pstmt.setObject(3, ticket.getTicketTicketType().name(), Types.OTHER);
            pstmt.setDate(4, Date.valueOf(ticket.getTicketCreationDate()));
            pstmt.executeUpdate();
        }
    }

    public Ticket fetchTicketById(char[] id) throws SQLException {
        String sql = "SELECT * FROM \"Ticket\" WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, new String(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Ticket(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        ticketTypes.valueOf(rs.getString("ticket_type")),
                        rs.getDate("creation_date").toLocalDate()
                );
            }
        }
        return null;
    }

    public List<Ticket> fetchTicketsByUserId(int userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM \"Ticket\" WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        ticketTypes.valueOf(rs.getString("ticket_type")),
                        rs.getDate("creation_date").toLocalDate()
                ));
            }
        }
        return tickets;
    }

    public void updateTicketType(int id, ticketTypes newType) throws SQLException {
        String sql = "UPDATE \"Ticket\" SET ticket_type = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, newType.name(),  Types.OTHER);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public void deleteTicketsByUserId(int userId) throws SQLException {
        String sql = "DELETE FROM \"Ticket\" WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
}