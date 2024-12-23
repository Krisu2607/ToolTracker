package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.Chamfer;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.Reamer;
import com.example.tooltracker.model.tools.ToolStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducentDAO {

    public List<String> getAllProducents() throws SQLException {
        List<String> producents = new ArrayList<>();
        String query = "SELECT * FROM Producent";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String prodNamde = resultSet.getString("producent_name");



                producents.add(prodNamde);
            }
        }

        return producents;
    }



    public void addCostByName(String prodName, BigDecimal amount) throws SQLException {
        String selectQuery = "SELECT total_expenses FROM Producent WHERE producent_name = ?";
        String updateQuery = "UPDATE Producent SET total_expenses = ? WHERE producent_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            selectStatement.setString(1, prodName);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal currentExpenses = resultSet.getBigDecimal("total_expenses");
                    BigDecimal newExpenses = currentExpenses.add(amount);

                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setBigDecimal(1, newExpenses);
                        updateStatement.setString(2, prodName);
                        updateStatement.executeUpdate();
                    }
                } else {
                    throw new SQLException("Producent o podanej nazwie nie istnieje.");
                }
            }
        }
    }

    public void addProducent(String prodName) throws SQLException {
        String query = "INSERT INTO producent (producent_name) VALUES (?)";
        try (  Connection connection = DatabaseUtil.getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, prodName);
            statement.executeUpdate();
        }
    }
}
