package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrillHssDAO  {

//    private static final String UPDATE_COMMENT = "UPDATE drillhss SET  comment=? WHERE toolIndex=?";


    private static final String UPDATE_TOOL_POST_SHARP = "UPDATE drillhss SET  toolStatus=?, diameter=?, length = ?, worklength=? WHERE toolIndex=?";

    public List<DrillHSS> getAllDrillHSS() throws SQLException {
        List<DrillHSS> tools = new ArrayList<>();
        String query = "SELECT * FROM DrillHSS";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                DrillHSS tool = new DrillHSS(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolType.DRILL_HSS,
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),

                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getDouble("diameter"),
                        resultSet.getInt("length"),
                        resultSet.getInt("worklength"),
                        resultSet.getInt("qty")

                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addDrillHSS(DrillHSS tool) throws SQLException {
        String query = "INSERT INTO DrillHSS (toolName, toolIndex, toolStatus, comment,price,  diameter, length, worklength) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getDiameter());
            statement.setInt(7, tool.getLength());
            statement.setInt(8, tool.getWorkLength());
            statement.executeUpdate();
        }
    }


    public void updatePostSharpen(DrillHSS drillHSS) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOL_POST_SHARP)) {
            preparedStatement.setString(1, "W_UZYCIU");
            preparedStatement.setInt(2, drillHSS.getLength());
            preparedStatement.setInt(3, drillHSS.getWorkLength());
            preparedStatement.setDouble(4, drillHSS.getDiameter());
            preparedStatement.setString(5, drillHSS.getToolIndex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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



        public DrillHSS getDrillHSSByIndex(String toolIndex) throws SQLException {
            String query = "SELECT * FROM DrillHSS WHERE toolIndex = ?";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, toolIndex);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new DrillHSS(
                                resultSet.getString("toolName"),
                                resultSet.getString("toolIndex"),
                                ToolType.DRILL_HSS,
                                ToolStatus.valueOf(resultSet.getString("toolStatus")),
                                resultSet.getString("comment"),
                                resultSet.getBigDecimal("price"),
                                resultSet.getDouble("diameter"),
                                resultSet.getInt("length"),
                                resultSet.getInt("worklength"),
                                resultSet.getInt("qty")
                        );
                    }
                }
            }
            return null; // Jeśli nie znaleziono narzędzia o podanym indeksie
        }

        // Metoda do aktualizacji ilości narzędzi
        public void updateDrillHSS(DrillHSS tool) throws SQLException {
            String query = "UPDATE DrillHSS SET qty = ? WHERE toolIndex = ?";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tool.getQty());
                statement.setString(2, tool.getToolIndex());
                statement.executeUpdate();
            }
        }

    public int getLastToolIndex() throws SQLException {
        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(toolIndex, '-', -1) AS UNSIGNED)) AS last_num FROM DrillHSS";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("last_num");
                }
            }
        }
        return 0; // Zwróć 0, jeśli nie znaleziono żadnych indeksów narzędzi
    }



    }


