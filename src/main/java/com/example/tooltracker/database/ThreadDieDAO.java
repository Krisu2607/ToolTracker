package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.ThreadDie;
import com.example.tooltracker.model.tools.ToolStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreadDieDAO {

    public List<ThreadDie> getAllThreadDie() throws SQLException {
        List<ThreadDie> tools = new ArrayList<>();
        String query = "SELECT * FROM threaddie";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {


                ThreadDie tool = new ThreadDie(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        MaterialType.valueOf(resultSet.getString("MaterialType")),
                        resultSet.getString("threadClass"),
                        resultSet.getDouble("metricsize"),
                        resultSet.getDouble("tapscroll"),
                        resultSet.getString("inchsize")



                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addThreadDie(ThreadDie tool) throws SQLException {
        String query = "INSERT INTO threaddie (toolName, toolIndex, toolStatus, comment,price, materialtype,  threadclass, metricsize, tapscroll, inchsize) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setString(6, tool.getMaterialType().name());
            statement.setString(7, tool.getThreadClass());
            statement.setDouble(8, tool.getMetricSize());
            statement.setDouble(9, tool.getDieScroll());
            statement.setString(10, tool.getInchsize());
            statement.executeUpdate();
        }
    }

    public int getLastToolIndex() throws SQLException {
        int lastToolIndexNum = 0;
        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(toolIndex, '-', -1) AS UNSIGNED)) AS lastToolIndexNum FROM threadDie";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lastToolIndexNum = resultSet.getInt("lastToolIndexNum");
                }
            }
        }
        return lastToolIndexNum;
    }
}
