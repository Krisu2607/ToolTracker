package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChamferDAO implements Tool1Dao  {

    private static final String UPDATE_TOOL_POST_SHARP = "UPDATE chamfer SET  toolStatus=?, l1=?, l2=?, d1=?, d2=? WHERE toolIndex=?";
    private static final String UPDATE_COMMENT = "UPDATE chamfer SET  comment=? WHERE toolIndex=?";

    private Connection connection;


    public List<Chamfer> getAllChemferTools() throws SQLException {
        List<Chamfer> tools = new ArrayList<>();
        String query = "SELECT * FROM Chamfer";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                Chamfer tool = new Chamfer(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),
                        resultSet.getDouble("d1"),
                        resultSet.getDouble("d2"),
                        resultSet.getInt("l1"),
                        resultSet.getInt("l2"),
                        resultSet.getInt("toothsQty"),
                        MaterialType.valueOf(resultSet.getString("MaterialType")),
                        resultSet.getDouble("angle")
                );
                tools.add(tool);
            }
        }

        return tools;
}


    public void addChamferTool(Chamfer tool) throws SQLException {
        String query = "INSERT INTO Chamfer (toolName, toolIndex, toolStatus, comment, price, d1, d2,l1,l2,toothsqty, MaterialType, angle, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getD1());
            statement.setDouble(7, tool.getD2());
            statement.setInt(8, tool.getL1());
            statement.setInt(9, tool.getL2());
            statement.setInt(10, tool.getToothsQty());
            statement.setString(11, tool.getMaterialType().toString());
            statement.setDouble(12, tool.getAngle());
            statement.setString(13, tool.getProducent());

            statement.executeUpdate();
        }
    }

    public void updatePostSharpen(Chamfer chamfer) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOL_POST_SHARP)) {
            preparedStatement.setString(1, "W_UZYCIU");
            preparedStatement.setInt(2, chamfer.getL1());
            preparedStatement.setInt(3, chamfer.getL2());
            preparedStatement.setDouble(4, chamfer.getD1());
            preparedStatement.setDouble(5, chamfer.getD2());
            preparedStatement.setString(6, chamfer.getToolIndex());
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


    public List<String> getToolIndexesByToothsQty(int toothsQty) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM Chamfer WHERE toothsQty = ?";
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

