package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrillBladesDAO implements Tool1Dao {
    private static final String UPDATE_COMMENT = "UPDATE drillblades SET  comment=? WHERE toolIndex=?";


    public List<DrillBlades> getAllDrillBlades() throws SQLException {
        List<DrillBlades> tools = new ArrayList<>();
        String query = "SELECT * FROM drillblades";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {


                DrillBlades tool = new DrillBlades(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getString("matchingInserts"),
                        resultSet.getDouble("diameter"),
                        resultSet.getInt("length"),
                        resultSet.getInt("toothsqty")

                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addDrillBladeTool(DrillBlades tool) throws SQLException {
        String query = "INSERT INTO DrillBlades (toolName, toolIndex, toolStatus, comment,price, matchingInserts, diameter, length, toothsQty, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setString(6, tool.getMatchingInserts());
            statement.setDouble(7, tool.getDiameter());
            statement.setInt(8, tool.getLength());
            statement.setInt(9, tool.getToothsQty());
            statement.setString(10, tool.getProducent());

            statement.executeUpdate();
        }
    }


    public void updateComment(Tool1 drillBlade) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, drillBlade.getComment());
            preparedStatement.setString(2, drillBlade.getToolIndex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastToolIndexNum(double diameter) throws SQLException {
        int lastToolIndexNum = 0;
        String query = "SELECT MAX(SUBSTRING_INDEX(toolIndex, '-', -1)) AS lastToolIndexNum FROM DrillBlades WHERE diameter = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, diameter);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lastToolIndexNum = resultSet.getInt("lastToolIndexNum");
                }
            }
        }
        return lastToolIndexNum;
    }

}
