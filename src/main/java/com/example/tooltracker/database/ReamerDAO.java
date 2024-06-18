package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReamerDAO {

//    private static final String UPDATE_COMMENT = "UPDATE reamer SET  comment=? WHERE toolIndex=?";

    private Connection connection;


    public List<Reamer> getAllReamerTools() throws SQLException {
        List<Reamer> tools = new ArrayList<>();
        String query = "SELECT * FROM Reamer";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                Reamer tool = new Reamer(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getDouble("diameter"),
                        MaterialType.valueOf(resultSet.getString("MaterialType")),
                        resultSet.getInt("length"
                        )
                );
                tools.add(tool);
            }
        }

        return tools;
    }


    public void addReamer(Reamer tool) throws SQLException {
        String query = "INSERT INTO Reamer (toolName, toolIndex, toolStatus, comment, price, diameter, MaterialType, length) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getDiameter());
            statement.setString(7, tool.getMaterial().toString());
            statement.setInt(8, tool.getLength());


            statement.executeUpdate();
        }
    }

//    public void updateComment(Tool1 tool1) {
//        try (Connection connection = DatabaseUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
//            preparedStatement.setString(1, tool1.getComment());
//            preparedStatement.setString(2, tool1.getToolIndex());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public List<String> getToolIndexes() throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM REAMER ";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }

}
