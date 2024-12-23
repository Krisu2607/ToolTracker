package com.example.tooltracker.database;

import com.example.tooltracker.model.Message;
import com.example.tooltracker.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {




    public List<Message> getAllMessages() {
        String sql = "SELECT * FROM messages ORDER BY timestamp";
        List<Message> messages = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Message message = new Message(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public void addMessage(String username, String message) {
        String sql = "INSERT INTO messages(username, message) VALUES(?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, message);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
