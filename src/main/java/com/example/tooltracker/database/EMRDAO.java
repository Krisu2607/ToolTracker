package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EMRDAO implements Tool1Dao  {

    private static final String UPDATE_COMMENT = "UPDATE emr SET  comment=? WHERE toolIndex=?";

    private Connection connection;



    public List<EmR> getAllEmRTools() throws SQLException {
        List<EmR> tools = new ArrayList<>();
        String query = "SELECT * FROM EMR";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                EmR tool = new EmR(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getBoolean("isitball"),
                        resultSet.getDouble("cornerr"),

                        resultSet.getInt("L1"),
                        resultSet.getInt("L2"),
                        resultSet.getDouble("d1"),
                        resultSet.getDouble("d2"),
                        MaterialType.valueOf(resultSet.getString("Material")),
                        resultSet.getInt("toothsQty"),
                        ToolDestinyMat.valueOf(resultSet.getString("toolDestinyMaterial"))
                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addEmrTool(EmR tool) throws SQLException {
        String query = "INSERT INTO EmR (toolName, toolIndex, toolStatus, comment, price, L1, L2, d1, d2, Material, toothsQty, isitball, cornerr, toolDestinyMaterial, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
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
            statement.setBoolean(12, tool.isIsItBall());
            statement.setDouble(13, tool.getCornerR());
            statement.setString(14, tool.getToolDestinyMat().toString());
            statement.setString(15, tool.getProducent());

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

    public List<String> getToolIndexesByToothsQty(int toothsQty) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM EMR WHERE toothsQty = ?";
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

    public List<String> getToolIndexesByCriteria(int toothsQty, boolean isBall, double radius, String materialCode) throws SQLException {
        String query;
        if (isBall) {
            query = "SELECT toolindex FROM Emr WHERE toothsQty = ? AND toolindex LIKE ? AND toolindex LIKE ?";
        } else {
            query = "SELECT toolindex FROM EmR WHERE toothsQty = ? AND toolindex LIKE ? AND toolindex LIKE ? AND toolindex LIKE ?";
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, toothsQty);
            if (isBall) {
                statement.setString(2, "BM-%" + materialCode + "%");
                statement.setString(3, "%" + toothsQty + "%");
            } else {
                statement.setString(2, "EM-R" + radius + "%");
                statement.setString(3, "%" + materialCode + "%");
                statement.setString(4, "%" + toothsQty + "%");
            }

            ResultSet resultSet = statement.executeQuery();
            List<String> indexes = new ArrayList<>();
            while (resultSet.next()) {
                indexes.add(resultSet.getString("toolindex"));
            }
            return indexes;
        }
    }
}
