package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsedToolsDAO {

//    private final DatabaseUtil databaseConnection;

//    public UsedToolsDAO() {
//        this.databaseConnection = new DatabaseUtil();
//    }
//
//    public ToolUsage getToolUsageByOrderNum(String orderNum) {
//        String query = "SELECT * FROM used_tools WHERE order_num = ?";
//        try (Connection connection = databaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, orderNum);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new ToolUsage(
//                        resultSet.getString("part_num"),
//                        resultSet.getString("order_num"),
//                        resultSet.getInt("ID"),
//                        resultSet.getInt("TOOL_INSERTS")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
    }

















//    public void updateToolInserts(ToolUsage toolUsage) {
//        String query = "UPDATE used_tools SET TOOL_INSERTS = ? WHERE ID = ?";
//        try (Connection connection = databaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, toolUsage.getToolInserts());
//            statement.setInt(2, toolUsage.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addToolUsage(ToolUsage toolUsage) {
//        String query = "INSERT INTO used_tools (order_num, part_num, TOOL_INSERTS) VALUES (?, ?, ?)";
//        try (Connection connection = databaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, toolUsage.getOrderNumber());
//            statement.setString(2, toolUsage.getPartNumber());
//            statement.setInt(3, toolUsage.getToolInserts());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



