package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TapPrDAO implements Tool1Dao {

    private static final String UPDATE_COMMENT = "UPDATE tappr SET  comment=? WHERE toolIndex=?";

    public List<TapPR> getAllPrTap() throws SQLException {
        List<TapPR> tools = new ArrayList<>();
        String query = "SELECT * FROM tappr";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String materialType = resultSet.getString("MaterialType");
                if(materialType == null || materialType.isEmpty()){
                    materialType = "HSS";
                }

                TapPR tool = new TapPR(

                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("producent"),

                        MaterialType.valueOf(materialType),
                        resultSet.getString("threadClass"),
                        resultSet.getDouble("metricsize"),
                        resultSet.getDouble("tapscroll")



                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addTapPR(TapPR tool) throws SQLException {
        String query = "INSERT INTO tappr (toolName, toolIndex, toolStatus, comment,price, materialtype,  threadclass, metricsize,  tapscroll, producent) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
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
            statement.setDouble(9, tool.getTapScroll());
            statement.setString(10, tool.getProducent());

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
        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(toolIndex, '-', -1) AS UNSIGNED)) AS lastToolIndexNum FROM TapPR";
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
