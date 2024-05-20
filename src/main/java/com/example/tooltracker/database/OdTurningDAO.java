package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.DrillBlades;
import com.example.tooltracker.model.tools.LatheToolTypes;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.TurningOD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdTurningDAO {

    public List<TurningOD> getAllOdTurnTools() throws SQLException {
        List<TurningOD> tools = new ArrayList<>();
        String query = "SELECT * FROM turningod";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                TurningOD tool = new TurningOD(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("matchingInserts"),
                        resultSet.getString("Type"),
                        resultSet.getString("workDirection"),
                        resultSet.getString("matchingBolt")




                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addOdTools(TurningOD tool) throws SQLException {
        String query = "INSERT INTO turningod (toolName, toolIndex, toolStatus, comment,price, matchingInserts, matchingBolt, workDirection,  type) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setString(6, tool.getMatchingInserts());
            statement.setString(7, tool.getMatchingBolt());
            statement.setString(8, tool.getCutDirection());
            statement.setString(9, tool.getWorkType());




            statement.executeUpdate();
        }
    }

    public List<String> getLastToolIndexByDirection(String cutDirection) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM turningod WHERE workdirection = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cutDirection);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }
}
