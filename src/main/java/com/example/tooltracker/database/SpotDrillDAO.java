package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpotDrillDAO implements Tool1Dao {

    private static final String UPDATE_COMMENT = "UPDATE spotdrill SET  comment=? WHERE toolIndex=?";


    private Connection connection;


    public List<SpotDrill> getAllSpotDrillTools() throws SQLException {
        List<SpotDrill> tools = new ArrayList<>();
        String query = "SELECT * FROM Spotdrill";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                SpotDrill tool = new SpotDrill(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getDouble("d1"),
                        resultSet.getDouble("d2"),
                        resultSet.getInt("length"),
                        MaterialType.valueOf(resultSet.getString("MaterialType"))
                );
                tools.add(tool);
            }
        }

        return tools;
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


    public void addSpotDrill(SpotDrill tool) throws SQLException {
        String query = "INSERT INTO Spotdrill (toolName, toolIndex, toolStatus, comment, price, d1, d2, MaterialType,length, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getD1());
            statement.setDouble(7, tool.getD2());
            statement.setString(8, tool.getMaterialType().toString());
            statement.setInt(9, tool.getLength());
            statement.setString(10, tool.getProducent());


            statement.executeUpdate();
        }
    }

    public List<String> getToolIndexesByMat(String material) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM Spotdrill WHERE MaterialType = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, material);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }

}
