package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.DrillBlades;
import com.example.tooltracker.model.tools.ShellMill;
import com.example.tooltracker.model.tools.ToolStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShellMillDAO {

    public List<ShellMill> getAllFFShellMill() throws SQLException {
        List<ShellMill> tools = new ArrayList<>();
        String query = "SELECT * FROM Shellmill WHERE ShellmillType = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Przenieś ustawienie wartości parametru wewnątrz bloku try
            statement.setString(1, "FF");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ShellMill tool = new ShellMill(
                            resultSet.getString("toolName"),
                            resultSet.getString("toolIndex"),
                            ToolStatus.valueOf(resultSet.getString("toolStatus")),
                            resultSet.getString("comment"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getString("matchingInserts"),
                            resultSet.getDouble("diameter"),
                            resultSet.getInt("toothsqty"),
                            resultSet.getBoolean("isiTIc"),
                            resultSet.getString("shellMillType"),
                            resultSet.getString("matchingBolt")

                    );
                    tools.add(tool);
                }
            }
        }
        return tools;
    }






    public List<ShellMill> getAllNMShellMill() throws SQLException {
        List<ShellMill> tools = new ArrayList<>();
        String query = "SELECT * FROM Shellmill WHERE ShellmillType = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Przenieś ustawienie wartości parametru wewnątrz bloku try
            statement.setString(1, "NM");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ShellMill tool = new ShellMill(
                            resultSet.getString("toolName"),
                            resultSet.getString("toolIndex"),
                            ToolStatus.valueOf(resultSet.getString("toolStatus")),
                            resultSet.getString("comment"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getString("matchingInserts"),
                            resultSet.getDouble("diameter"),
                            resultSet.getInt("toothsqty"),
                            resultSet.getBoolean("isiTIc"),
                            resultSet.getString("shellMillType"),
                            resultSet.getString("matchingBolt")
                    );
                    tools.add(tool);
                }
            }
        }
        return tools;
    }

    public void addShellMills(ShellMill tool) throws SQLException {
        String query = "INSERT INTO Shellmill (toolName, toolIndex, toolStatus, comment,price, matchingInserts, diameter, toothsQty, isItIc, shellMillType, matchingBolt) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setString(6, tool.getMatchingInserts());
            statement.setDouble(7, tool.getDiameter());
            statement.setInt(8, tool.getToothsQty());
            statement.setBoolean(9, tool.isIsItIc());
            statement.setString(10, tool.getShellMillType());
            statement.setString(11, tool.getMatchingBolt());
            statement.executeUpdate();
        }
    }

    public List<String> getLastToolIndexByShellMillType(String shellMillType) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM shellmill WHERE shellMillType = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shellMillType);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }

}
