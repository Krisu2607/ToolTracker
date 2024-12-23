package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TapInchDAO implements Tool1Dao  {

    private static final String UPDATE_COMMENT = "UPDATE tapinch SET  comment=? WHERE toolIndex=?";

    public List<TapInch> getallTapInch() throws SQLException {
        List<TapInch> tools = new ArrayList<>();
        String query = "SELECT * FROM tapinch";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                TapInch tool = new TapInch(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        MaterialType.valueOf(resultSet.getString("MaterialType")),
                        resultSet.getString("inchSize")




                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addTapInch(TapInch tool) throws SQLException {
        String query = "INSERT INTO tapinch (toolName, toolIndex, toolStatus, comment,price, materialtype,inchsize, producent) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setString(6, tool.getMaterialType().name());
            statement.setString(7, tool.getInchSize());
            statement.setString(8, tool.getProducent());

            statement.executeUpdate();
        }
    }


    public void updateComment(Tool1 tool1) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, tool1.getComment());
            preparedStatement.setString(2, tool1.getToolIndex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int getLastToolIndex() throws SQLException {
        int lastToolIndexNum = 0;
        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(toolIndex, '-', -1) AS UNSIGNED)) AS lastToolIndexNum FROM Tapinch";
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
