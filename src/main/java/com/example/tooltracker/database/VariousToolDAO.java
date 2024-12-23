package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.DrillVHM;
import com.example.tooltracker.model.tools.Tool1;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.VariousTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VariousToolDAO implements Tool1Dao {

    private static final String UPDATE_COMMENT = "UPDATE variuoustool SET  comment=? WHERE toolIndex=?";

    @Override


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

    public List<VariousTool> getAllVariousTools() throws SQLException {
        List<VariousTool> tools = new ArrayList<>();
        String query = "SELECT * FROM variuoustool";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                VariousTool tool = new VariousTool(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getDouble("tooldiameter"),
                        resultSet.getString("tooltype")

                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addVariousTool(VariousTool tool) throws SQLException {
        String query = "INSERT INTO variuoustool (toolName, toolIndex, toolStatus, comment,price,  tooldiameter, tooltype, producent) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getDiameter());
            statement.setString(7, tool.getToolWorkType());
            statement.setString(8, tool.getProducent());


            statement.executeUpdate();
        }
    }
}
