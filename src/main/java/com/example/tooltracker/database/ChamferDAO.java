package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.Chamfer;
import com.example.tooltracker.model.tools.EmMet;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.ToolStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChamferDAO {

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
        String query = "INSERT INTO Chamfer (toolName, toolIndex, toolStatus, comment, price, d1, d2,l1,l2,toothsqty, MaterialType, angle) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
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
            statement.executeUpdate();
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

