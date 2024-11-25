package dao;

import model.users.Client;
import java.sql.*;

public class UserDAO {
    private final String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private final String user = "postgres";
    private final String password = "1111";

    public void saveUser(Client client){
        String sql = "INSERT INTO \"User\"  (name, creation_date) VALUES (?, ?)";
        try(Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, client.getName());
            pstmt.setDate(2, Date.valueOf(client.getCreationDate()));
            pstmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Cannot update the database");
            ex.printStackTrace();

        }
    }

    public Client fetchUserById(int id){
        String sql = "SELECT * FROM \"User\" WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url, user,password); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Client(rs.getString("name"), rs.getInt("id"), rs.getDate("creation_date").toLocalDate());
            }
        }catch(SQLException ex){
            System.out.println("Cannot fetch user by this ID");
        }
        return null;
    }

    public void deleteUserById(int id)  {
        String sql = "DELETE FROM \"User\" WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Cannot delete user by this ID");

        }
    }
}
