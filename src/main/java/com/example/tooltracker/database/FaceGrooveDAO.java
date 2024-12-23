package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FaceGrooveDAO implements Tool1Dao  {

    private static final String UPDATE_COMMENT = "UPDATE facegroove SET  comment=? WHERE toolIndex=?";


    public List<FaceGroove> getAllFaceGrooveTools() throws SQLException {
        List<FaceGroove> tools = new ArrayList<>();
        String query = "SELECT * FROM facegroove";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                FaceGroove tool = new FaceGroove(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolType.FACE_GROOVE,
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        resultSet.getDouble("minDiamCut"),
                        resultSet.getDouble("maxDiamCut"),
                        resultSet.getInt("maxDepth"),
                        resultSet.getString("matchingInserts"),
                        resultSet.getString("matchingBolt"),
                        resultSet.getString("workDirection")

                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addFaceGrooveTools(FaceGroove tool) throws SQLException {
        String query = "INSERT INTO facegroove (toolName, toolIndex, toolStatus, comment,price, minDiamCut, maxDiamCut, maxDepth, matchingInserts, matchingBolt, workDirection, producent) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getMinDiamCut());
            statement.setDouble(7, tool.getMaxDiamCut());
            statement.setInt(8, tool.getMaxDepth());
            statement.setString(9, tool.getMatchingInserts());
            statement.setString(10, tool.getMatchingBolt());
            statement.setString(11, tool.getWorkDirection());
            statement.setString(12, tool.getProducent());






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

    public List<String> getLastToolIndexByDirection(String cutDirection) throws SQLException {
        List<String> toolIndexes = new ArrayList<>();
        String query = "SELECT toolIndex FROM facegroove WHERE workdirection = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cutDirection);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    toolIndexes.add(resultSet.getString("toolIndex"));
                }
            }
        }
        return toolIndexes;
    }
}
