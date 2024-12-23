package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmAluDAO implements Tool1Dao {

    private static final String UPDATE_COMMENT = "UPDATE emalu SET  comment=? WHERE toolIndex=?";

    private static final String UPDATE_TOOL_POST_SHARP = "UPDATE emalu SET  toolStatus=?,  l1=?, l2=?, d1=?, d2=? WHERE toolIndex=?";

    private Connection connection;



    public List<EmAlu> getAllEmAluTools() throws SQLException {
        List<EmAlu> tools = new ArrayList<>();
        String query = "SELECT * FROM EmAlu";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                EmAlu tool = new EmAlu(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getInt("L1"),
                        resultSet.getInt("L2"),
                        resultSet.getDouble("d1"),
                        resultSet.getDouble("d2"),
                        MaterialType.valueOf(resultSet.getString("Material")),
                        resultSet.getInt("toothsQty")
                );
                tools.add(tool);
            }
        }
        return tools;
    }


    public void updatePostSharpen(EmAlu emAlu) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOL_POST_SHARP)) {
            preparedStatement.setString(1, "W_UZYCIU");
            preparedStatement.setInt(2, emAlu.getL1());
            preparedStatement.setInt(3, emAlu.getL2());
            preparedStatement.setDouble(4, emAlu.getD1());
            preparedStatement.setDouble(5, emAlu.getD2());
            preparedStatement.setString(6, emAlu.getToolIndex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void addEmAluTool(EmAlu tool) throws SQLException {
        String query = "INSERT INTO EmAlu (toolName, toolIndex, toolStatus, comment, price, L1, L2, d1, d2, Material, toothsQty, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setInt(6, tool.getL1());
            statement.setInt(7, tool.getL2());
            statement.setDouble(8, tool.getD1());
            statement.setDouble(9, tool.getD2());
            statement.setString(10, tool.getMaterial().toString());
            statement.setInt(11, tool.getToothsQty());
            statement.setString(12, tool.getProducent());

            statement.executeUpdate();
        }
    }

    public List<String> getToolIndexesByToothsQty(int toothsQty) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM EmAlu WHERE toothsQty = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, toothsQty);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }




}


