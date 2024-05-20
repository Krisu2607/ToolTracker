package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.DrillHSS;
import com.example.tooltracker.model.tools.DrillVHM;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.ToolType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrillvhmDAO {

    public List<DrillVHM> getAllDrillHSS() throws SQLException {
        List<DrillVHM> tools = new ArrayList<>();
        String query = "SELECT * FROM DrillVHM";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                DrillVHM tool = new DrillVHM(
                        resultSet.getString("toolName"),
                        resultSet.getString("toolIndex"),
                        ToolStatus.valueOf(resultSet.getString("toolStatus")),
                        resultSet.getString("comment"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getDouble("diameter"),
                        resultSet.getInt("length"),
                        resultSet.getInt("worklength"),
                        resultSet.getBoolean("isinternalcooled")

                );
                tools.add(tool);
            }
        }
        return tools;
    }

    public void addDrillVHM(DrillVHM tool) throws SQLException {
        String query = "INSERT INTO DrillVHM (toolName, toolIndex, toolStatus, comment,price,  diameter, length, worklength, isinternalcooled) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tool.getToolName());
            statement.setString(2, tool.getToolIndex());
            statement.setString(3, "W_UZYCIU");
            statement.setString(4, tool.getComment());
            statement.setBigDecimal(5, tool.getPrice());
            statement.setDouble(6, tool.getDiameter());
            statement.setInt(7, tool.getLength());
            statement.setInt(8, tool.getWorkLength());
            statement.setBoolean(9, tool.isInternalCooled());
            statement.executeUpdate();
        }
    }

//    public int getLastToolIndexNum(boolean isIc) throws SQLException {
//        String prefix;
//        if (isIc) {
//            prefix = "DR-VHM-IC";
//        } else {
//            prefix = "DR-VHM";
//        }
//
//        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(toolindex, '-', -1) AS UNSIGNED)) AS last_num " +
//                "FROM drillvhm " +
//                "WHERE toolindex LIKE ?";
//
//        int lastNum = 0;
//
//        try (Connection connection = DatabaseUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, prefix + "-%");
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    lastNum = resultSet.getInt("last_num");
//                }
//            }
//        }
//
//        // Sprawdź, czy ostatni numer został pobrany
//        if (lastNum == 0) {
//            return lastNum;
//        }
//
//        // Jeśli ostatni numer został pobrany, zwróć go
//        return lastNum;
//    }

    public int getLastToolIndexNum(boolean isIc) throws SQLException {
        String query = "SELECT toolindex FROM DrillVHM WHERE isinternalcooled = ?";
        List<String> indices = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, isIc ? 1 : 0);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    indices.add(resultSet.getString("toolindex"));
                }
            }
        }

        // Przetwarzanie wyników po stronie Javy, aby znaleźć ostatni numer
        int lastNum = 0;
        String prefix = isIc ? "DR-VHM-IC-" : "DR-VHM-";
        for (String index : indices) {
            if (index.startsWith(prefix)) {
                String numStr = index.substring(prefix.length());
                try {
                    int num = Integer.parseInt(numStr);
                    if (num > lastNum) {
                        lastNum = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignorujemy błędne formaty numerów
                }
            }
        }

        return lastNum;
    }

}
